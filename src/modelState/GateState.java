package modelState;

import java.io.Serializable;

import expressionHandling.NumericExpression;

import modelView.GateTab;

import physicalObjects.HHCurrent;
import physicalObjects.HHGate;

public class GateState implements Serializable {

	private static final long serialVersionUID = 1L;

	private NumericExpression tau;
	private NumericExpression inf;
	private NumericExpression alpha;
	private NumericExpression beta;
	private int        exponent;
	private boolean    enabled;
	private String name;
	private transient GateTab ui;
	private transient HHGate  physicalGate;
	private transient HHCurrent physicalCurrent;
	
	public GateState(GateTab ui, CurrentState owningCurrent, NumericExpression tau, NumericExpression inf, NumericExpression alpha, NumericExpression beta) {
		this.tau   = tau;
		this.inf   = inf;
		this.alpha = alpha;
		this.beta  = beta;
		this.name = ui.getName();
		this.ui = ui;
		this.physicalCurrent = (HHCurrent) owningCurrent.getPhysicalCurrent();
		
		physicalGate = new HHGate(name, physicalCurrent, tau, inf, alpha, beta);
		setExponent(1);
		setEnabled(true);
	}

	public NumericExpression getTau() {
		return tau;
	}
	
	public NumericExpression getInf() {
		return inf;
	}
	
	public NumericExpression getAlpha() {
		return alpha;
	}
	
	public NumericExpression getBeta() {
		return beta;
	}
	
	public void setExponent(int i) {
		physicalGate.setExponent(i);
		exponent = i;
	}

	public void setEnabled(boolean selected) {
		physicalGate.setEnabled(selected);
		enabled = selected;
		ui.setEnabled(selected);
	}

	public HHGate getPhysicalGate() {
		return physicalGate;
	}

	public void setUI(GateTab ui) {
		this.ui = ui;
		physicalGate = new HHGate(name, physicalCurrent, tau, inf, alpha, beta);
		ui.setName(name);
		ui.setExponent(exponent);
		ui.setEnabled(enabled);
		physicalGate.setExponent(exponent);
		physicalGate.setEnabled(enabled);
	}

	public void setPhysicalCurrent(HHCurrent physicalCurrent) {
		this.physicalCurrent = physicalCurrent;		
	}

	public void setName(String s) { 
		name = s; 
		physicalGate.setName(s);
	}

	public void setDoPlots(boolean e) {
		physicalGate.setDoPlots(e);
		ui.setDoPlots(e);
	}
}
