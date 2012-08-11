package expressionHandling;

import java.io.Serializable;

import javax.swing.text.JTextComponent;

public abstract class Expression implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String expr;
	protected transient JTextComponent tf;
	protected transient boolean valid;

	public Expression(JTextComponent tf) {
		this.tf = tf;
	}
	
	public String getExpr() { return expr; }

	public String toString() { return expr; }

	public void setUI(JTextComponent t) {
		this.tf = t;
		setString(expr);
	}
	
	public boolean isValid() { return valid; }

	public abstract void setString(String expr);
	public abstract void textFieldUpdate();
}
