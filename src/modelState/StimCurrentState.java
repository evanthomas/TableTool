package modelState;

import expressionHandling.NumericExpression;
import modelView.CurrentTab;
import modelView.ModelDesignerView;
import modelView.StimCurrentTab;
import physicalObjects.StimCurrent;

public class StimCurrentState extends CurrentState {

	private static final long serialVersionUID = 1L;
	
	private NumericExpression stimulus;
	
	public StimCurrentState(StimCurrentTab ui, String name, NumericExpression stimulus) {
		super(ui, name);
		this.stimulus = stimulus;
		physicalCurrent = new StimCurrent(name, stimulus);
	}


	public StimCurrent getPhysicalCurrent() {
		return (StimCurrent)physicalCurrent;
	}
	

	public void setUI(CurrentTab ui) {
		physicalCurrent = new StimCurrent(name, stimulus);
		super.setUI(ui);
	}
	
	public void restore(ModelDesignerView ui) {
		new StimCurrentTab(ui.getCurrentTabs(), this);
	}


	public NumericExpression getStimulus() {
		return stimulus;
	}
}
