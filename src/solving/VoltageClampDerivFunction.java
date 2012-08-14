package solving;

import java.util.ArrayList;

import modelState.AppState;
import modelState.CurrentState;
import physicalObjects.Current;
import physicalObjects.HHCurrent;
import physicalObjects.HHGate;
import physicalObjects.StimCurrent;
import expressionParsing.ParseException;

public class VoltageClampDerivFunction implements AbstractDerivFunction {

	private int N; // Dimension of the state vector
	
	private ArrayList<CurrentState> currentList;
	private ArrayList<HHCurrent>    hhCurrentList;
	private ArrayList<StimCurrent>  voltageStimList;
	private ArrayList<HHGate>       gateList;
	private double [] currentCurrent;
	private double currentVoltage;

	public VoltageClampDerivFunction() throws ParseException {
		currentList = AppState.getCurrentList();
		
		hhCurrentList = new ArrayList<HHCurrent>();
		voltageStimList = new ArrayList<StimCurrent>();
		gateList = new ArrayList<HHGate>();
		
		int currentCnt = 0;
		for(int i=0; i<currentList.size(); i++) {
			Current c = currentList.get(i).getPhysicalCurrent();
			if( !c.enabled() ) continue;
			if( c.getIncludeInPlots() && !(c instanceof StimCurrent) ) currentCnt++;
			
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
			
			if( c instanceof StimCurrent ) voltageStimList.add((StimCurrent) c);
		}
		
		// Query gates and currents and determine data capture for plots
		currentCurrent = new double[currentCnt];
	}
	
	@Override
	public void computeDerivatives(double t, double[] x, double[] dxdt) {
		
		currentVoltage = 0;
		for(int i=0; i<voltageStimList.size(); i++) {
			StimCurrent c = voltageStimList.get(i);
			currentVoltage += c.I(t);
		}
		
		int currentIndx = 0;
		for(int i=0; i<hhCurrentList.size(); i++) {
			HHCurrent c = hhCurrentList.get(i); 
			currentCurrent[currentIndx++] = c.I(currentVoltage);
		}
		
		for(int i=0; i<N; i++) dxdt[i] = gateList.get(i).deriv(currentVoltage, x[i]);

		return;
	}

	@Override
	public int getDimension() {	return N; }

	@Override
	public double[] initialConditions(double v) {
		double[] state = new double[N];
		
		for(int i=0; i<N; i++) state[i] = gateList.get(i).initialCondition(v);
		
		return state;
	}

	@Override
	public double[] getCurrentCurrent() { return currentCurrent; }

	@Override
	public double    getCurrentVoltage() { return currentVoltage; }
}
