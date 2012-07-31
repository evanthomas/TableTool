package modelState;

import java.io.Serializable;

import modelView.Expression;
import modelView.GateTab;

import physicalObjects.HHCurrent;
import physicalObjects.HHGate;

public class GateState implements Serializable {

	private static final long serialVersionUID = 1L;

	private Expression tau;
	private Expression inf;
	private Expression alpha;
	private Expression beta;
	private int        exponent;
	private boolean    enabled;
	private boolean    doPlots;
	private String name;
	private GateTab ui;
	private transient HHGate  physicalGate;
	private transient HHCurrent physicalCurrent;
	
	public GateState(GateTab ui, CurrentState owningCurrent, Expression tau, Expression inf, Expression alpha, Expression beta) {
		this.tau   = tau;
		this.inf   = inf;
		this.alpha = alpha;
		this.beta  = beta;
		this.name = ui.getName();
		this.ui = ui;
		this.physicalCurrent = (HHCurrent) owningCurrent.getPhysicalCurrent();
		
		physicalGate = new HHGate(physicalCurrent, tau, inf, alpha, beta);
		setExponent(1);
		setEnabled(true);
	}

	public Expression getTau() {
		return tau;
	}
	
	public Expression getInf() {
		return inf;
	}
	
	public Expression getAlpha() {
		return alpha;
	}
	
	public Expression getBeta() {
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
		physicalGate = new HHGate(physicalCurrent, tau, inf, alpha, beta);
		ui.setName(name);
		ui.setExponent(exponent);
		ui.setEnabled(enabled);
		physicalGate.setExponent(exponent);
		physicalGate.setEnabled(enabled);
	}

	public void setPhysicalCurrent(HHCurrent physicalCurrent) {
		this.physicalCurrent = physicalCurrent;		
	}

	public void setName(String s) { name = s; }

	public void setDoPlots(boolean e) {
		this.doPlots = e;
		physicalGate.setDoPlots(e);
		ui.setDoPlots(e);
	}
}
