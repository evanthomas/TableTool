package modelState;

import modelView.CurrentTab;
import modelView.Expression;
import modelView.ModelDesignerView;
import modelView.StepCurrentTab;
import physicalObjects.StepCurrent;
import physicalObjects.StimCurrent;

public class StepCurrentState extends CurrentState {

	private static final long serialVersionUID = 1L;
	
	private Expression start;
	private Expression end;
	private Expression step;
	
	public StepCurrentState(StepCurrentTab ui, String name, Expression start, Expression end, Expression step) {
		super(ui, name);
		this.start = start;
		this.end = end;
		this.step = step;
		physicalCurrent = new StepCurrent(start, end, step);
	}


	public StimCurrent getPhysicalCurrent() {
		return (StimCurrent)physicalCurrent;
	}
	

	public void setUI(CurrentTab ui) {
		physicalCurrent = new StepCurrent(start, end, step);
		super.setUI(ui);
	}
	
	public void restore(ModelDesignerView ui) {
		new StepCurrentTab(ui.getCurrentTabs(), this);
	}


	public Expression getStart() {
		return start;
	}


	public Expression getEnd() {
		return end;
	}


	public Expression getStep() {
		return step;
	}
}
