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
import expressionEvaluator.ExpressionGrammarParser.symbol_return;
//import expressionEvaluator.ExpressionGrammarParser.symbolExpression_return;

public class Symbol {

	private String expr;
	private ExpressionEvaluator gf;
	private CommonTreeNodeStream nodeStream;
	
	public Symbol(String expr) throws ParseException {
		this.expr = expr;
		// Parse the expression and generate the AST
		CharStream stream = new ANTLRStringStream(expr);
		ExpressionGrammarLexer lexer = new ExpressionGrammarLexer(stream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		ExpressionGrammarParser parser = new ExpressionGrammarParser(tokenStream);
		symbol_return gfParser = null;
		
		try {
			gfParser = parser.symbol();
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
		
	public String eval() throws ParseException {
		nodeStream.rewind(0);
		try {
			return gf.symbolName();
		} catch (RecognitionException e) {
			throw(new ParseException(e.getMessage()));
		}
	}
}
