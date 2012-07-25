package solving;

import java.util.ArrayList;

import expressionEvaluator.ParseException;

import modelBuild.AppState;
import modelBuild.CurrentState;

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
	
	private double Cm;
	
	public CurrentClampDerivFunction() throws ParseException {
		currentList = AppState.getCurrentList();
		
		hhCurrentList = new ArrayList<HHCurrent>();
		stimCurrentList = new ArrayList<StimCurrent>();
		gateList = new ArrayList<HHGate>();
		
		Cm = AppState.getCapacitance(); // May throw exception

		N = 1; // There is always voltage
		for(int i=0; i<currentList.size(); i++) {
			Current c = currentList.get(i).getPhysicalCurrent();
			if( !c.enabled() ) continue;
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
	}

	@Override
	public void computeDerivatives(double t, double[] x, double[] dxdt) {
		double v = x[N-1];
		for(int i=0; i<N-1; i++) dxdt[i] = gateList.get(i).deriv(v, x[i]);
		
		double I = 0;
		for(int i=0; i<stimCurrentList.size(); i++) I += stimCurrentList.get(i).I(t);
		for(int i=0; i<hhCurrentList.size(); i++)   I += hhCurrentList.get(i).I(v);
		
		dxdt[N-1] = I/Cm;
		
		return;
	}

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
