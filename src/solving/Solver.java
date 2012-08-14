package solving;

import java.util.ArrayList;

import modelState.AppState;
import modelState.CurrentState;

import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator;
import org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator;
import org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;
import org.apache.commons.math3.ode.nonstiff.GillIntegrator;
import org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator;
import org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator;
import org.apache.commons.math3.ode.nonstiff.MidpointIntegrator;
import org.apache.commons.math3.ode.nonstiff.ThreeEighthesIntegrator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

import physicalObjects.Current;
import physicalObjects.HHCurrent;
import physicalObjects.HHGate;
import physicalObjects.StimCurrent;
import plotting.ODESolutionPlotter;
import expressionParsing.ParseException;

public class Solver implements Runnable {

	private static double MINSTEP =  1e-3; // ms
	private static double MAXSTEP =   1.0; // ms
	private static int ADAMSORDER =     4;
	private static double V_INIT  =   -90; // mV
	
	private AbstractDerivFunction ccdf;
	private double[] yinit;
	private FirstOrderIntegrator integrator;
	private double rundur;
	private double[] state;
	private ArrayList<Double> time;
	private ArrayList<Double> voltage;
	private ArrayList<double[]> accumulatedState;
	private ArrayList<double[]> accumulatedCurrent;
	private ClampMode clampMode;
	
	public Solver() throws ParseException {
		SolverType method = AppState.getOdeMethod();
		double tolerance  = AppState.getTolerance();
		rundur            = AppState.getRundur();
		clampMode         = AppState.getClampMode();
		
		switch(method) {
		case Euler:
			integrator = new EulerIntegrator(tolerance);
			break;
		case midpoint:
			integrator = new MidpointIntegrator(tolerance);
			break;
		case RungeKutta:
			integrator = new ClassicalRungeKuttaIntegrator(tolerance);
			break;
		case Gill:
			integrator = new GillIntegrator(tolerance);
			break;
		case ThreeEight:
			integrator = new ThreeEighthesIntegrator(tolerance);
			break;
		case HighamHill:
			integrator = new HighamHall54Integrator(MINSTEP, MAXSTEP, tolerance, tolerance);
			break;
		case DormandPrince54:
			integrator = new DormandPrince54Integrator(MINSTEP, MAXSTEP, tolerance, tolerance);
			break;
		case DormandPrince853:
			integrator = new DormandPrince853Integrator(MINSTEP, MAXSTEP, tolerance, tolerance);
			break;
		case GraggBurlischStoer:
			integrator = new GraggBulirschStoerIntegrator(MINSTEP, MAXSTEP, tolerance, tolerance);
			break;
		case AdamsBashforth:
			integrator = new AdamsBashforthIntegrator(ADAMSORDER, MINSTEP, MAXSTEP, tolerance, tolerance);
			break;
		case AdamsMoulton:
			integrator = new AdamsMoultonIntegrator(ADAMSORDER, MINSTEP, MAXSTEP, tolerance, tolerance);
			break;
		default:
			assert(false);		
		}
		
		switch(clampMode) {
			case CURRENT_CLAMP:
				ccdf = new CurrentClampDerivFunction();
				break;
			case VOLTAGE_CLAMP:
				ccdf = new VoltageClampDerivFunction();
				break;
		}
		yinit = ccdf.initialConditions(V_INIT);		
		StepHandler stepHandler = new StepHandler() {
		    public void init(double t0, double[] y0, double t) {}
		            
		    public void handleStep(StepInterpolator interpolator, boolean isLast) {
		        double   t = interpolator.getCurrentTime();
		        double[] y = interpolator.getInterpolatedState();
		        time.add(t);
		        voltage.add(ccdf.getCurrentVoltage());
		        accumulatedState.add(y.clone());
		        accumulatedCurrent.add(ccdf.getCurrentCurrent().clone());
		    }
		};
		
		integrator.addStepHandler(stepHandler);
		state = new double[ccdf.getDimension()];
		time = new ArrayList<Double>();
		voltage = new ArrayList<Double>();
		accumulatedState = new ArrayList<double[]>();
		accumulatedCurrent = new ArrayList<double[]>();
	}

	public void run() {
		integrator.integrate(ccdf, 0.0, yinit, rundur, state);
		
		plotVoltage();
		plotCurrent();
		plotGates();
	}
	
	private void plotVoltage() {
		if( !AppState.doVoltagePlots() ) return;
		
		double t[] = new double[time.size()];
		for(int i=0; i<time.size(); i++) {
			t[i] = time.get(i);
		}
		
		new ODESolutionPlotter("Voltage", t, getVoltage(), new String[] {"voltage"});
	}
	
	private void plotCurrent() {
		if( !AppState.doCurrentPlots() ) return;
		ClampMode clampMode = AppState.getClampMode();
		
		// Find out which currents are to be plotted
		int numCurrents = 0;
		ArrayList<CurrentState> currentList = AppState.getCurrentList();
		for(int i=0; i<currentList.size(); i++) {
			Current c = currentList.get(i).getPhysicalCurrent();
			if( !c.enabled() ) continue;
			if( !c.getIncludeInPlots() ) continue;
			if( clampMode==ClampMode.VOLTAGE_CLAMP && c instanceof StimCurrent ) continue;
			numCurrents++;
		}
		
		// Reformat data for plotter, extract names
		double [] t = new double[time.size()];
		double [][] I = new double[numCurrents][time.size()];
		String [] currentNames = new String [numCurrents];
		for(int i=0; i<time.size(); i++) {
			t[i] = time.get(i);
			int currentIndx = 0;
			for(int j=0; j<currentList.size(); j++) {
				Current c = currentList.get(j).getPhysicalCurrent();
				if( !c.enabled() ) continue;
				if( !c.getIncludeInPlots() ) continue;
				if( clampMode==ClampMode.VOLTAGE_CLAMP && c instanceof StimCurrent ) continue;
				I[currentIndx++][i] = accumulatedCurrent.get(i)[j];
				currentNames[j] = c.getName(); // Shouldn't be in the inner loop
			}
		}

		new ODESolutionPlotter("Current", t, I, currentNames);
	}
	
	private void plotGates() {
		if( !AppState.doGatePlots() ) return;
		
		int numGates = 0;
		ArrayList<CurrentState> currentList = AppState.getCurrentList();

		// Find gates to be plotted
		for(int i=0; i<currentList.size(); i++) {
			Current c = currentList.get(i).getPhysicalCurrent();
			if( !c.enabled() ) continue;
			
			if( c instanceof HHCurrent ) {
				HHCurrent hhc = (HHCurrent) c;
				ArrayList<HHGate> gl = hhc.getGateList();
				for(int j=0; j<gl.size(); j++) {
					HHGate g = gl.get(j);
					if( !g.enabled() ) continue;
					if( !g.includeInPlots() ) continue;
					
					numGates++;
				}
			}
		}
		
		// Get names for the legend
		String [] gateNames = new String[numGates];
		int gateIndx = 0;
		for(int i=0; i<currentList.size(); i++) {
			Current c = currentList.get(i).getPhysicalCurrent();
			if( !c.enabled() ) continue;
			
			if( c instanceof HHCurrent ) {
				HHCurrent hhc = (HHCurrent) c;
				ArrayList<HHGate> gl = hhc.getGateList();
				for(int j=0; j<gl.size(); j++) {
					HHGate g = gl.get(j);
					if( !g.enabled() ) continue;
					if( !g.includeInPlots() ) continue;
					
					gateNames[gateIndx++] = c.getName() + " - " + g.getName();
				}
			}
		}
		
		// Get data and reformat for plotter
		double [][] m = new double[numGates][time.size()];
		double [] t = new double[time.size()];
		for(int i=0; i<time.size(); i++) {
			t[i] = time.get(i);
			gateIndx = 0;
			int stateIndx = -1;
			for(int j=0; j<currentList.size(); j++) {
				Current c = currentList.get(j).getPhysicalCurrent();
				if( !c.enabled() ) continue;
				
				if( c instanceof HHCurrent ) {
					for(HHGate g : ((HHCurrent) c).getGateList()) {
						stateIndx++;
						
						if( !g.enabled() ) continue;
						if( !g.includeInPlots() ) continue;
						
						m[gateIndx++][i] = accumulatedState.get(i)[stateIndx];
					}
				}
			}
		}

		new ODESolutionPlotter("Gates", t, m, gateNames);
	}
	
	private double[][] getVoltage() {
		int n = voltage.size();
		double[][] voltageArray = new double[1][n];
		int N = ccdf.getDimension();
		for(int i=0; i<n; i++) {
			voltageArray[0][i] = voltage.get(i);
		}
		return voltageArray;
	}
	
	public void go() {
		new Thread(this).start();
	}
}
