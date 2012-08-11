package expressionHandling;

import java.awt.Color;

import javax.swing.text.JTextComponent;

import expressionParsing.ParseException;
import expressionParsing.Symbol;

public class SymbolExpression extends Expression {

	private static final long serialVersionUID = 1L;
	
	private transient Symbol s;
	
	public SymbolExpression(JTextComponent tf) {
		super(tf);
	}

	@Override
	public void setString(String expr) {
		this.expr = expr;
		tf.setText(expr);
		valid = false;
		s = null;
		
		// Don't bug the user by complaining about stuff it isn't filled in yet
		if( expr==null || expr.equals("")) return; 

		try {
			s = new Symbol(expr);
		} catch (ParseException e) {
			tf.setBackground(Color.RED);
			return;
		}
		
		tf.setBackground(Color.WHITE);
		valid = true;
		}

	@Override
	public void textFieldUpdate() {
		String expr = tf.getText();
		try {
			setStringOnly(expr);
		} catch(ParseException ex) {
			tf.setBackground(Color.RED);
			return;
		}
		tf.setBackground(Color.WHITE);
	}
	
	private void setStringOnly(String expr) throws ParseException {
		this.expr = expr;
		valid = false;
		s = null;
		if( expr==null || expr.equals("")) return; // Don't bug the user by complaining about stuff that isn't filled in yet
		s = new Symbol(expr);
		valid = true;
	}
}
