package expressionHandling;

import java.awt.Color;

import javax.swing.text.JTextComponent;

import expressionParsing.Function;
import expressionParsing.ParseException;

public class NumericExpression extends Expression {
	
	private static final long serialVersionUID = 1L;

	private transient Function f;
	
	public NumericExpression(JTextComponent tf) {
		super(tf);
		valid = false;
		f = null;
	}
	
	private void setStringOnly(String expr) throws ParseException {
		this.expr = expr;
		valid = false;
		f = null;
		if( expr==null || expr.equals("")) return; // Don't bug the user by complaining about stuff that isn't filled in yet
		f = new Function(expr);
		valid = true;
	}
	
	public double eval() throws ParseException {
		return f.eval();
	}
	
	public double evalV(double x) throws ParseException {
		return f.evalV(x);
	}
	
	public double evalT(double x) throws ParseException {
		return f.evalT(x);
	}
	
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
	
	public void setString(String expr) {
		this.expr = expr;
		tf.setText(expr);
		valid = false;
		f = null;
		
		// Don't bug the user by complaining about stuff it isn't filled in yet
		if( expr==null || expr.equals("")) return; 

		try {
			f = new Function(expr);
		} catch (ParseException e) {
			tf.setBackground(Color.RED);
			return;
		}
		
		tf.setBackground(Color.WHITE);
		valid = true;
	}
}
