package modelState;

import java.io.Serializable;

import modelView.CurrentTab;
import modelView.ModelDesignerView;

import physicalObjects.Current;

public abstract class CurrentState implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean doPlots;
	private boolean enabled;
	private String notes;
	protected String name;
	protected transient CurrentTab ui;
	protected transient Current physicalCurrent;

	public CurrentState(CurrentTab ui, String name) {
		doPlots = true;
		enabled = true;
		this.ui = ui;
		this.name = name;
	}

	public void setEnabled(boolean e) {
		this.enabled = e;
		physicalCurrent.setEnabled(e);
		ui.setEnabled(e);
	}
	
	public void setNotes(String s) { this.notes = s; }
	
	public void setDoPlots(boolean e) {
		this.doPlots = e;
		physicalCurrent.setIncludeInPlots(e);
		ui.setDoPlots(e);
	}
	
	public Current getPhysicalCurrent() {
		return physicalCurrent;
	}

	public void setUI(CurrentTab ui) {
		this.ui = ui;
		ui.setEnabled(enabled);
		ui.setDoPlots(doPlots);
		ui.setNotes(notes);
		ui.setName(name);
	}

	public void setName(String newname) {
		name = newname;
		physicalCurrent.setName(newname);
	}

	public abstract void restore(ModelDesignerView ui);
}
