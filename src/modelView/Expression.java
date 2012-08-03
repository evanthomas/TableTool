package modelView;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.text.JTextComponent;

import expressionEvaluator.Function;
import expressionEvaluator.ParseException;

public class Expression implements Serializable {


	private static final long serialVersionUID = 1L;

	private String expr;
	
	private transient Function f;
	private transient boolean valid;
	private transient JTextComponent tf;
	
	public Expression(JTextComponent tf) {
		this.tf = tf;
		valid = false;
		f = null;
	}
	
	private void setStringOnly(String expr) throws ParseException {
		this.expr = expr;
		valid = false;
		f = null;
		if( expr==null || expr.equals("")) return; // Don't bug the user by complaining about stuff it hasn't filled in yet
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
	
	public String getExpr() { return expr; }
	public boolean isValid() { return valid; }

	public void textFieldHandler() {
		String expr = tf.getText();
		try {
			setStringOnly(expr);
		} catch(ParseException ex) {
			System.out.println(ex.getMessage());
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
	
	public String toString() { return "Expr: "+expr+"  Function "+f; }

	public void setUI(JTextComponent t) {
		this.tf = t;
		setString(expr);
	}
}
