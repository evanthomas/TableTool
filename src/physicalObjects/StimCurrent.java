package physicalObjects;

import modelView.Expression;
import modelView.PopupHelper;
import expressionEvaluator.ParseException;

public class StimCurrent extends Current {
	
	private Expression stimulus;
	
	public StimCurrent(String name, Expression stimulus) {
		super(name);
		this.stimulus = stimulus;
	}

	public double I(double t) {
		try {
			return stimulus.evalT(t);
		} catch (ParseException e) {
			PopupHelper.errorMessage(null, "unexpected ParseException thrown during run.");
			e.printStackTrace();
		}
		return 0;
	}
	
	public void initialise() throws ParseException {
		// If we're gonna throw an exception, do it now.
		// This comparison stops the compiler optimising out
		// the evaluation (don't actually know if this can happen).
		try {
			if( stimulus.evalT(0) == Double.POSITIVE_INFINITY )
				throw new ParseException();
		} catch (NullPointerException npe) { // Occurs when text field is empty - couldn't find a more elegant solution
			throw new ParseException(super.getName()+" is undefined or invalid.");
		}
		return; 
	}
}
