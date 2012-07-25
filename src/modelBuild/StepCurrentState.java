package modelBuild;

import physicalObjects.StepCurrent;
import physicalObjects.StimCurrent;

public class StepCurrentState extends CurrentState {

	private static final long serialVersionUID = 1L;
	
	private double start;
	private double end;
	private double duration;
	
	public StepCurrentState(CurrentTab ui, String name, double start, double end, double duration) {
		super(ui, name);
		this.start = start;
		this.end = end;
		this.duration = duration;
		physicalCurrent = new StepCurrent(start, end, duration);
	}


	public StimCurrent getPhysicalCurrent() {
		return (StimCurrent)physicalCurrent;
	}
	

	public void setUI(CurrentTab ui) {
		physicalCurrent = new StepCurrent(start, end, duration);
//		super.setUI(ui);
	}
}
