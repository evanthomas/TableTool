package solving;

import java.util.ArrayList;

import expressionEvaluator.ParseException;

import modelState.AppState;
import modelState.CurrentState;

import physicalObjects.Current;
import physicalObjects.HHCurrent;
import physicalObjects.HHGate;
import physicalObjects.StimCurrent;

public class CurrentClampDerivFunction implements AbstractDerivFunction {

	private int N; // Dimension of the state vector
	
	private ArrayList<CurrentState> currentList;
	private ArrayList<HHCurrent>    hhCurrentList;
	private ArrayList<StimCurrent>  stimCurrentList;
	private ArrayList<HHGate>       gateList;
	private double [] currentCurrent;
	
	private double Cm;
	
	public CurrentClampDerivFunction() throws ParseException {
		currentList = AppState.getCurrentList();
		
		hhCurrentList = new ArrayList<HHCurrent>();
		stimCurrentList = new ArrayList<StimCurrent>();
		gateList = new ArrayList<HHGate>();
		
		Cm = AppState.getCapacitance(); // May throw exception

		int currentCnt = 0;
		N = 1; // There is always voltage
		for(int i=0; i<currentList.size(); i++) {
			Current c = currentList.get(i).getPhysicalCurrent();
			if( !c.enabled() ) continue;
			if( c.getIncludeInPlots() ) currentCnt++;
			
			c.initialise();
			
			if( c instanceof HHCurrent ) {
				HHCurrent hhc = (HHCurrent) c;
				hhCurrentList.add(hhc);
				ArrayList<HHGate> gl = hhc.getGateList();
				for(int j=0; j<gl.size(); j++) {
					HHGate g = gl.get(j);
					if( !g.enabled() ) continue;
					g.initialise();
					gateList.add(g);
					N++;
				}
			}
			
			if( c instanceof StimCurrent ) stimCurrentList.add((StimCurrent) c);
		}
		
		// Query gates and currents and determine data capture for plots
		currentCurrent = new double[currentCnt];
	}

	@Override
	public void computeDerivatives(double t, double[] x, double[] dxdt) {
		double v = x[N-1];
		for(int i=0; i<N-1; i++) dxdt[i] = gateList.get(i).deriv(v, x[i]);
		
		double totalI = 0, I=0;
		int currentIndx = 0;
		for(int i=0; i<stimCurrentList.size(); i++) {
			StimCurrent c = stimCurrentList.get(i);
			I = c.I(t);
			totalI += I;
			currentCurrent[currentIndx++] = I;
		}
		for(int i=0; i<hhCurrentList.size(); i++) {
			HHCurrent c =hhCurrentList.get(i); 
			I = c.I(v);
			totalI += I;
			currentCurrent[currentIndx++] = I;
		}
		
		dxdt[N-1] = totalI/Cm;
		
		return;
	}
	
	public double [] getCurrentCurrent() { return currentCurrent; }

	@Override
	public int getDimension() {
		return N;
	}

	@Override
	public double[] initialConditions(double v) {
		double[] state = new double[N];
		
		for(int i=0; i<N-1; i++) state[i] = gateList.get(i).initialCondition(v);
		
		state[N-1] = v;
		
		return state;
	}
}
