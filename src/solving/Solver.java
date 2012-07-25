package solving;

import java.util.ArrayList;

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

import plotting.ODESolutionPlotter;

import expressionEvaluator.ParseException;
import modelBuild.AppState;

public class Solver implements Runnable {

	private static double MINSTEP =  1e-3; // ms
	private static double MAXSTEP =   1.0; // ms
	private static int ADAMSORDER =     4;
	private static double V_INIT  =   -90; // mV
	
	private CurrentClampDerivFunction ccdf;
	double[] yinit;
	FirstOrderIntegrator integrator;
	double rundur;
	double[] state;
	private ArrayList<Double> time;
	private ArrayList<double[]> accumulatedState;
	
	public Solver() throws ParseException {
		SolverType method = AppState.getOdeMethod();
		double tolerance  = AppState.getTolerance();
		rundur            = AppState.getRundur();
		
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
		
		ccdf = new CurrentClampDerivFunction();
		yinit = ccdf.initialConditions(V_INIT);		
		StepHandler stepHandler = new StepHandler() {
		    public void init(double t0, double[] y0, double t) {}
		            
		    public void handleStep(StepInterpolator interpolator, boolean isLast) {
		        double   t = interpolator.getCurrentTime();
		        double[] y = interpolator.getInterpolatedState();
		        time.add(t);
		        accumulatedState.add(y.clone());
		    }
		};
		integrator.addStepHandler(stepHandler);
		state = new double[ccdf.getDimension()];
		time = new ArrayList<Double>();
		accumulatedState = new ArrayList<double[]>();
	}

	public void run() {
		integrator.integrate(ccdf, 0.0, yinit, rundur, state);
		new ODESolutionPlotter("Voltage", time, getVoltage());
	}
	
	private double[] getVoltage() {
		int n = accumulatedState.size();
		double[] voltage = new double[n];
		int N = ccdf.getDimension();
		for(int i=0; i<n; i++) {
			voltage[i] = accumulatedState.get(i)[N-1];
		}
		return voltage;
	}
	
	public void go() {
		new Thread(this).start();
	}
}
