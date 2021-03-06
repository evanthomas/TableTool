package modelState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import expressionHandling.NumericExpression;

import modelView.CurrentTab;
import modelView.HHCurrentTab;
import modelView.ModelDesignerView;

import physicalObjects.HHCurrent;

// Proxy object between physical current and GUI
public class HHCurrentState extends CurrentState {

	private static final long serialVersionUID = 1L;
	
	private NumericExpression reversal;
	private NumericExpression conductance;
	private ArrayList<GateState> gates;

	public ArrayList<GateState> getGates() {
		return gates;
	}

	public HHCurrentState(CurrentTab ui, String name, NumericExpression conductance, NumericExpression reversal) {
		super(ui, name);
		this.reversal = reversal;
		this.conductance = conductance;
		
		physicalCurrent = new HHCurrent(name, conductance, reversal);
		gates = new ArrayList<GateState>();
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
	}
	
	public void removeGate(GateState gateState) {
		gates.remove(gateState);		
	}
	
	public void addGate(GateState g) {
		gates.add(g);
	}
	
	public HHCurrent getPhysicalCurrent() {
		return (HHCurrent)physicalCurrent;
	}

	public NumericExpression getReversal() {
		return reversal;
	}

	public NumericExpression getConductance() {
		return conductance;
	}


	public void setUI(HHCurrentTab ui) {
		physicalCurrent = new HHCurrent(name, conductance, reversal);
		super.setUI(ui);
		
		int N = gates.size();
		for(int i=0; i<N; i++) {
			GateState g = gates.get(i);
			g.setPhysicalCurrent((HHCurrent) physicalCurrent);
			ui.restoreTab(g);
		}
	}
	
	public void restore(ModelDesignerView ui) {
		new HHCurrentTab(ui.getCurrentTabs(), this);
	}

}
