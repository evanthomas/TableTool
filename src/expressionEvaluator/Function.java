package expressionEvaluator;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import expressionEvaluator.GateGrammarParser.gateExpression_return;

public class Function {

	private String expr;
	private GateFunctionEvaluator gf;
	private CommonTreeNodeStream nodeStream;
	
	public Function(String expr) throws ParseException {
		this.expr = expr;
		// Parse the expression and generate the AST
		CharStream stream = new ANTLRStringStream(expr);
		GateGrammarLexer lexer = new GateGrammarLexer(stream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		GateGrammarParser parser = new GateGrammarParser(tokenStream);
		gateExpression_return gfParser = null;
		
		try {
			gfParser = parser.gateExpression();
		} catch (RecognitionException e) {
			throw new ParseException("All your input are crap.");
		}
		
		if( !parser.getErrors().isEmpty() ) {
			// Just report the first error
			throw new ParseException(parser.getErrors().get(0));
		}
		
		// Generate the function from the AST
		nodeStream= new CommonTreeNodeStream(gfParser.tree); 
		gf = new GateFunctionEvaluator(nodeStream);		
	}
	
	public String getExpr() { return expr; }
	
	public double eval() throws ParseException { return eval(0); }
	
	public double eval(double v) throws ParseException {
		nodeStream.rewind(0);
		gf.setV(v);
		try {
			return gf.gateFunction();
		} catch (RecognitionException e) {
			throw(new ParseException(e.getMessage()));
		}
	}
}
