package physicalObjects;

import expressionEvaluator.ParseException;
import modelView.Expression;

public class StepCurrent extends StimCurrent {
	
	private Expression startE;
	private Expression endE;
	private Expression stepE;
	
	private double start;
	private double end;
	private double step;
	
	public StepCurrent(Expression startE, Expression endE, Expression stepE) {
		super("Step current");
		this.startE = startE;
		this.endE = endE;
		this.stepE = stepE;
	}

	public double I(double t) {
		return this.start<=t && t<=this.end ? step : 0;
	}
	
	public void initialise() throws ParseException {
		start = startE.eval();
		end = endE.eval();
		step = stepE.eval();		
	}
}
