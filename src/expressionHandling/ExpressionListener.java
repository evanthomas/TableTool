package expressionHandling;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class ExpressionListener implements DocumentListener {

	private Expression expression;
	
	public ExpressionListener(Expression e) {
		this.expression = e;
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		expression.textFieldUpdate();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		expression.textFieldUpdate();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		expression.textFieldUpdate();
	}

}
