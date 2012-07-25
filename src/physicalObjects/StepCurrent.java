package physicalObjects;

public class StepCurrent extends StimCurrent {

	private double startTime;
	private double endTime;
	private double step;
	
	public StepCurrent(double startTime, double endTime, double step) {
		super("Step current");
		this.startTime = startTime;
		this.endTime = endTime;
		this.step = step;
	}

	public double I(double t) {
		return this.startTime<=t && t<=this.endTime ? step : 0;
	}

}
