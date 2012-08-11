package expressionParsing;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import expressionEvaluator.ExpressionEvaluator;
import expressionEvaluator.ExpressionGrammarLexer;
import expressionEvaluator.ExpressionGrammarParser;
import expressionEvaluator.ExpressionGrammarParser.gateExpression_return;

public class Function {

	private String expr;
	private ExpressionEvaluator gf;
	private CommonTreeNodeStream nodeStream;
	
	public Function(String expr) throws ParseException {
		this.expr = expr;
		// Parse the expression and generate the AST
		CharStream stream = new ANTLRStringStream(expr);
		ExpressionGrammarLexer lexer = new ExpressionGrammarLexer(stream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		ExpressionGrammarParser parser = new ExpressionGrammarParser(tokenStream);
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
		nodeStream= new CommonTreeNodeStream(gfParser.getTree()); 
		gf = new ExpressionEvaluator(nodeStream);		
	}
	
	public String getExpr() { return expr; }
	
	public double evalV(double v) throws ParseException { 
		gf.setV(v);
		return eval();
	}
	
	public double evalT(double t) throws ParseException {
		gf.setT(t);
		return eval();
	}
	
	public double eval() throws ParseException {
		nodeStream.rewind(0);
		try {
			return gf.gateFunction();
		} catch (RecognitionException e) {
			throw(new ParseException(e.getMessage()));
		}
	}
}
