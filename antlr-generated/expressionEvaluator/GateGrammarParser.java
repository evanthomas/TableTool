// $ANTLR 3.4 D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g 2012-07-24 14:02:56

package expressionEvaluator;

import java.util.LinkedList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class GateGrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "DIGIT", "DOT", "EXPONENT", "FLOAT", "LETTER", "MATH", "NEGATION", "SIGN", "VOLTAGE", "WS", "'!'", "'!='", "'&&'", "'('", "')'", "'*'", "'+'", "','", "'-'", "'/'", "'<'", "'<='", "'=='", "'>'", "'>='", "'^'", "'if'", "'||'"
    };

    public static final int EOF=-1;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int DIGIT=4;
    public static final int DOT=5;
    public static final int EXPONENT=6;
    public static final int FLOAT=7;
    public static final int LETTER=8;
    public static final int MATH=9;
    public static final int NEGATION=10;
    public static final int SIGN=11;
    public static final int VOLTAGE=12;
    public static final int WS=13;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public GateGrammarParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public GateGrammarParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return GateGrammarParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g"; }



        private List<String> errors = new LinkedList<String>();
        public void displayRecognitionError(String[] tokenNames,
                                            RecognitionException e) {
            String hdr = getErrorHeader(e);
            String msg = getErrorMessage(e, tokenNames);
            errors.add(hdr + " " + msg);
        }
        public List<String> getErrors() {
            return errors;
        }

    private void checkSingleMath(String fn) throws RecognitionException {
    if( fn.equals("exp") ) return;
    if( fn.equals("sin") ) return;
    if( fn.equals("cos") ) return;
    if( fn.equals("tan") ) return;
    if( fn.equals("abs") ) return;
    throw new RecognitionException();
    }

    private void checkDoubleMath(String fn) throws RecognitionException {
    if( fn.equals("min") ) return;
    if( fn.equals("max") ) return;
    throw new RecognitionException();
    }



    public static class gateExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "gateExpression"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:55:1: gateExpression : add EOF !;
    public final GateGrammarParser.gateExpression_return gateExpression() throws RecognitionException {
        GateGrammarParser.gateExpression_return retval = new GateGrammarParser.gateExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token EOF2=null;
        GateGrammarParser.add_return add1 =null;


        CommonTree EOF2_tree=null;

        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:55:16: ( add EOF !)
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:55:18: add EOF !
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_add_in_gateExpression77);
            add1=add();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, add1.getTree());

            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_gateExpression79); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "gateExpression"


    public static class term_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "term"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:57:1: term : ( FLOAT | VOLTAGE | '(' ! add ')' !| MATH ^ '(' ! add ')' !| MATH ^ '(' ! add ',' ! add ')' !| ternary );
    public final GateGrammarParser.term_return term() throws RecognitionException {
        GateGrammarParser.term_return retval = new GateGrammarParser.term_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token FLOAT3=null;
        Token VOLTAGE4=null;
        Token char_literal5=null;
        Token char_literal7=null;
        Token MATH8=null;
        Token char_literal9=null;
        Token char_literal11=null;
        Token MATH12=null;
        Token char_literal13=null;
        Token char_literal15=null;
        Token char_literal17=null;
        GateGrammarParser.add_return add6 =null;

        GateGrammarParser.add_return add10 =null;

        GateGrammarParser.add_return add14 =null;

        GateGrammarParser.add_return add16 =null;

        GateGrammarParser.ternary_return ternary18 =null;


        CommonTree FLOAT3_tree=null;
        CommonTree VOLTAGE4_tree=null;
        CommonTree char_literal5_tree=null;
        CommonTree char_literal7_tree=null;
        CommonTree MATH8_tree=null;
        CommonTree char_literal9_tree=null;
        CommonTree char_literal11_tree=null;
        CommonTree MATH12_tree=null;
        CommonTree char_literal13_tree=null;
        CommonTree char_literal15_tree=null;
        CommonTree char_literal17_tree=null;

        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:58:3: ( FLOAT | VOLTAGE | '(' ! add ')' !| MATH ^ '(' ! add ')' !| MATH ^ '(' ! add ',' ! add ')' !| ternary )
            int alt1=6;
            switch ( input.LA(1) ) {
            case FLOAT:
                {
                alt1=1;
                }
                break;
            case VOLTAGE:
                {
                alt1=2;
                }
                break;
            case 17:
                {
                alt1=3;
                }
                break;
            case MATH:
                {
                int LA1_4 = input.LA(2);

                if ( (synpred4_GateGrammar()) ) {
                    alt1=4;
                }
                else if ( (synpred5_GateGrammar()) ) {
                    alt1=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 4, input);

                    throw nvae;

                }
                }
                break;
            case 30:
                {
                alt1=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }

            switch (alt1) {
                case 1 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:58:5: FLOAT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    FLOAT3=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_term91); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT3_tree = 
                    (CommonTree)adaptor.create(FLOAT3)
                    ;
                    adaptor.addChild(root_0, FLOAT3_tree);
                    }

                    }
                    break;
                case 2 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:59:5: VOLTAGE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    VOLTAGE4=(Token)match(input,VOLTAGE,FOLLOW_VOLTAGE_in_term97); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VOLTAGE4_tree = 
                    (CommonTree)adaptor.create(VOLTAGE4)
                    ;
                    adaptor.addChild(root_0, VOLTAGE4_tree);
                    }

                    }
                    break;
                case 3 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:60:5: '(' ! add ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal5=(Token)match(input,17,FOLLOW_17_in_term103); if (state.failed) return retval;

                    pushFollow(FOLLOW_add_in_term106);
                    add6=add();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, add6.getTree());

                    char_literal7=(Token)match(input,18,FOLLOW_18_in_term108); if (state.failed) return retval;

                    }
                    break;
                case 4 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:61:5: MATH ^ '(' ! add ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    MATH8=(Token)match(input,MATH,FOLLOW_MATH_in_term115); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MATH8_tree = 
                    (CommonTree)adaptor.create(MATH8)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(MATH8_tree, root_0);
                    }

                    char_literal9=(Token)match(input,17,FOLLOW_17_in_term118); if (state.failed) return retval;

                    pushFollow(FOLLOW_add_in_term121);
                    add10=add();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, add10.getTree());

                    char_literal11=(Token)match(input,18,FOLLOW_18_in_term123); if (state.failed) return retval;

                    if ( state.backtracking==0 ) {checkSingleMath((MATH8!=null?MATH8.getText():null));}

                    }
                    break;
                case 5 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:62:5: MATH ^ '(' ! add ',' ! add ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    MATH12=(Token)match(input,MATH,FOLLOW_MATH_in_term132); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MATH12_tree = 
                    (CommonTree)adaptor.create(MATH12)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(MATH12_tree, root_0);
                    }

                    char_literal13=(Token)match(input,17,FOLLOW_17_in_term135); if (state.failed) return retval;

                    pushFollow(FOLLOW_add_in_term138);
                    add14=add();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, add14.getTree());

                    char_literal15=(Token)match(input,21,FOLLOW_21_in_term140); if (state.failed) return retval;

                    pushFollow(FOLLOW_add_in_term143);
                    add16=add();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, add16.getTree());

                    char_literal17=(Token)match(input,18,FOLLOW_18_in_term145); if (state.failed) return retval;

                    if ( state.backtracking==0 ) {checkDoubleMath((MATH12!=null?MATH12.getText():null));}

                    }
                    break;
                case 6 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:63:5: ternary
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_ternary_in_term154);
                    ternary18=ternary();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ternary18.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "term"


    public static class unary_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unary"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:66:1: unary : ( '+' !| negation ^)? term ;
    public final GateGrammarParser.unary_return unary() throws RecognitionException {
        GateGrammarParser.unary_return retval = new GateGrammarParser.unary_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal19=null;
        GateGrammarParser.negation_return negation20 =null;

        GateGrammarParser.term_return term21 =null;


        CommonTree char_literal19_tree=null;

        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:67:3: ( ( '+' !| negation ^)? term )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:68:3: ( '+' !| negation ^)? term
            {
            root_0 = (CommonTree)adaptor.nil();


            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:68:3: ( '+' !| negation ^)?
            int alt2=3;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==20) ) {
                alt2=1;
            }
            else if ( (LA2_0==22) ) {
                alt2=2;
            }
            switch (alt2) {
                case 1 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:68:4: '+' !
                    {
                    char_literal19=(Token)match(input,20,FOLLOW_20_in_unary173); if (state.failed) return retval;

                    }
                    break;
                case 2 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:68:11: negation ^
                    {
                    pushFollow(FOLLOW_negation_in_unary178);
                    negation20=negation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(negation20.getTree(), root_0);

                    }
                    break;

            }


            pushFollow(FOLLOW_term_in_unary183);
            term21=term();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, term21.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unary"


    public static class negation_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "negation"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:71:1: negation : '-' -> NEGATION ;
    public final GateGrammarParser.negation_return negation() throws RecognitionException {
        GateGrammarParser.negation_return retval = new GateGrammarParser.negation_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal22=null;

        CommonTree char_literal22_tree=null;
        RewriteRuleTokenStream stream_22=new RewriteRuleTokenStream(adaptor,"token 22");

        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:72:1: ( '-' -> NEGATION )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:73:1: '-'
            {
            char_literal22=(Token)match(input,22,FOLLOW_22_in_negation195); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_22.add(char_literal22);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 73:5: -> NEGATION
            {
                adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(NEGATION, "NEGATION")
                );

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "negation"


    public static class power_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "power"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:76:1: power : unary ( '^' ^ unary )* ;
    public final GateGrammarParser.power_return power() throws RecognitionException {
        GateGrammarParser.power_return retval = new GateGrammarParser.power_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal24=null;
        GateGrammarParser.unary_return unary23 =null;

        GateGrammarParser.unary_return unary25 =null;


        CommonTree char_literal24_tree=null;

        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:77:1: ( unary ( '^' ^ unary )* )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:78:1: unary ( '^' ^ unary )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_unary_in_power208);
            unary23=unary();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unary23.getTree());

            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:78:7: ( '^' ^ unary )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==29) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:78:8: '^' ^ unary
            	    {
            	    char_literal24=(Token)match(input,29,FOLLOW_29_in_power211); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal24_tree = 
            	    (CommonTree)adaptor.create(char_literal24)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal24_tree, root_0);
            	    }

            	    pushFollow(FOLLOW_unary_in_power214);
            	    unary25=unary();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unary25.getTree());

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "power"


    public static class mult_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mult"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:81:1: mult : power ( ( '*' ^| '/' ^) power )* ;
    public final GateGrammarParser.mult_return mult() throws RecognitionException {
        GateGrammarParser.mult_return retval = new GateGrammarParser.mult_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal27=null;
        Token char_literal28=null;
        GateGrammarParser.power_return power26 =null;

        GateGrammarParser.power_return power29 =null;


        CommonTree char_literal27_tree=null;
        CommonTree char_literal28_tree=null;

        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:82:3: ( power ( ( '*' ^| '/' ^) power )* )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:83:3: power ( ( '*' ^| '/' ^) power )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_power_in_mult229);
            power26=power();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, power26.getTree());

            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:83:10: ( ( '*' ^| '/' ^) power )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==19||LA5_0==23) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:83:11: ( '*' ^| '/' ^) power
            	    {
            	    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:83:11: ( '*' ^| '/' ^)
            	    int alt4=2;
            	    int LA4_0 = input.LA(1);

            	    if ( (LA4_0==19) ) {
            	        alt4=1;
            	    }
            	    else if ( (LA4_0==23) ) {
            	        alt4=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 4, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt4) {
            	        case 1 :
            	            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:83:12: '*' ^
            	            {
            	            char_literal27=(Token)match(input,19,FOLLOW_19_in_mult234); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal27_tree = 
            	            (CommonTree)adaptor.create(char_literal27)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal27_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:83:19: '/' ^
            	            {
            	            char_literal28=(Token)match(input,23,FOLLOW_23_in_mult239); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal28_tree = 
            	            (CommonTree)adaptor.create(char_literal28)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal28_tree, root_0);
            	            }

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_power_in_mult243);
            	    power29=power();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, power29.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mult"


    public static class add_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "add"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:86:1: add : mult ( ( '+' ^| '-' ^) mult )* ;
    public final GateGrammarParser.add_return add() throws RecognitionException {
        GateGrammarParser.add_return retval = new GateGrammarParser.add_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal31=null;
        Token char_literal32=null;
        GateGrammarParser.mult_return mult30 =null;

        GateGrammarParser.mult_return mult33 =null;


        CommonTree char_literal31_tree=null;
        CommonTree char_literal32_tree=null;

        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:87:3: ( mult ( ( '+' ^| '-' ^) mult )* )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:88:3: mult ( ( '+' ^| '-' ^) mult )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_mult_in_add258);
            mult30=mult();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, mult30.getTree());

            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:88:8: ( ( '+' ^| '-' ^) mult )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==20||LA7_0==22) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:88:9: ( '+' ^| '-' ^) mult
            	    {
            	    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:88:9: ( '+' ^| '-' ^)
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0==20) ) {
            	        alt6=1;
            	    }
            	    else if ( (LA6_0==22) ) {
            	        alt6=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 6, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt6) {
            	        case 1 :
            	            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:88:10: '+' ^
            	            {
            	            char_literal31=(Token)match(input,20,FOLLOW_20_in_add262); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal31_tree = 
            	            (CommonTree)adaptor.create(char_literal31)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal31_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:88:17: '-' ^
            	            {
            	            char_literal32=(Token)match(input,22,FOLLOW_22_in_add267); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal32_tree = 
            	            (CommonTree)adaptor.create(char_literal32)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal32_tree, root_0);
            	            }

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_mult_in_add271);
            	    mult33=mult();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, mult33.getTree());

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "add"


    public static class ternary_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ternary"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:91:1: ternary : 'if' ^ '(' ! logical ',' ! add ',' ! add ')' !;
    public final GateGrammarParser.ternary_return ternary() throws RecognitionException {
        GateGrammarParser.ternary_return retval = new GateGrammarParser.ternary_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal34=null;
        Token char_literal35=null;
        Token char_literal37=null;
        Token char_literal39=null;
        Token char_literal41=null;
        GateGrammarParser.logical_return logical36 =null;

        GateGrammarParser.add_return add38 =null;

        GateGrammarParser.add_return add40 =null;


        CommonTree string_literal34_tree=null;
        CommonTree char_literal35_tree=null;
        CommonTree char_literal37_tree=null;
        CommonTree char_literal39_tree=null;
        CommonTree char_literal41_tree=null;

        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:92:4: ( 'if' ^ '(' ! logical ',' ! add ',' ! add ')' !)
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:93:4: 'if' ^ '(' ! logical ',' ! add ',' ! add ')' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal34=(Token)match(input,30,FOLLOW_30_in_ternary290); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal34_tree = 
            (CommonTree)adaptor.create(string_literal34)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal34_tree, root_0);
            }

            char_literal35=(Token)match(input,17,FOLLOW_17_in_ternary293); if (state.failed) return retval;

            pushFollow(FOLLOW_logical_in_ternary296);
            logical36=logical();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, logical36.getTree());

            char_literal37=(Token)match(input,21,FOLLOW_21_in_ternary298); if (state.failed) return retval;

            pushFollow(FOLLOW_add_in_ternary301);
            add38=add();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, add38.getTree());

            char_literal39=(Token)match(input,21,FOLLOW_21_in_ternary303); if (state.failed) return retval;

            pushFollow(FOLLOW_add_in_ternary306);
            add40=add();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, add40.getTree());

            char_literal41=(Token)match(input,18,FOLLOW_18_in_ternary308); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "ternary"


    public static class logical_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "logical"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:96:1: logical : ( comparison | '(' or ')' );
    public final GateGrammarParser.logical_return logical() throws RecognitionException {
        GateGrammarParser.logical_return retval = new GateGrammarParser.logical_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal43=null;
        Token char_literal45=null;
        GateGrammarParser.comparison_return comparison42 =null;

        GateGrammarParser.or_return or44 =null;


        CommonTree char_literal43_tree=null;
        CommonTree char_literal45_tree=null;

        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:97:3: ( comparison | '(' or ')' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==FLOAT||LA8_0==MATH||LA8_0==VOLTAGE||LA8_0==20||LA8_0==22||LA8_0==30) ) {
                alt8=1;
            }
            else if ( (LA8_0==17) ) {
                int LA8_2 = input.LA(2);

                if ( (LA8_2==FLOAT||LA8_2==MATH||LA8_2==VOLTAGE||LA8_2==17||LA8_2==20||LA8_2==22||LA8_2==30) ) {
                    alt8=1;
                }
                else if ( (LA8_2==14) ) {
                    alt8=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:97:5: comparison
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_comparison_in_logical326);
                    comparison42=comparison();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comparison42.getTree());

                    }
                    break;
                case 2 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:98:5: '(' or ')'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal43=(Token)match(input,17,FOLLOW_17_in_logical332); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal43_tree = 
                    (CommonTree)adaptor.create(char_literal43)
                    ;
                    adaptor.addChild(root_0, char_literal43_tree);
                    }

                    pushFollow(FOLLOW_or_in_logical334);
                    or44=or();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, or44.getTree());

                    char_literal45=(Token)match(input,18,FOLLOW_18_in_logical336); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal45_tree = 
                    (CommonTree)adaptor.create(char_literal45)
                    ;
                    adaptor.addChild(root_0, char_literal45_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "logical"


    public static class not_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "not"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:101:2: not : '!' ^ logical ;
    public final GateGrammarParser.not_return not() throws RecognitionException {
        GateGrammarParser.not_return retval = new GateGrammarParser.not_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal46=null;
        GateGrammarParser.logical_return logical47 =null;


        CommonTree char_literal46_tree=null;

        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:102:4: ( '!' ^ logical )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:102:6: '!' ^ logical
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal46=(Token)match(input,14,FOLLOW_14_in_not353); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal46_tree = 
            (CommonTree)adaptor.create(char_literal46)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(char_literal46_tree, root_0);
            }

            pushFollow(FOLLOW_logical_in_not356);
            logical47=logical();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, logical47.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "not"


    public static class and_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "and"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:105:1: and : not ( '&&' ^ not )* ;
    public final GateGrammarParser.and_return and() throws RecognitionException {
        GateGrammarParser.and_return retval = new GateGrammarParser.and_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal49=null;
        GateGrammarParser.not_return not48 =null;

        GateGrammarParser.not_return not50 =null;


        CommonTree string_literal49_tree=null;

        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:106:3: ( not ( '&&' ^ not )* )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:106:5: not ( '&&' ^ not )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_not_in_and370);
            not48=not();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, not48.getTree());

            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:106:9: ( '&&' ^ not )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==16) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:106:10: '&&' ^ not
            	    {
            	    string_literal49=(Token)match(input,16,FOLLOW_16_in_and373); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal49_tree = 
            	    (CommonTree)adaptor.create(string_literal49)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(string_literal49_tree, root_0);
            	    }

            	    pushFollow(FOLLOW_not_in_and376);
            	    not50=not();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, not50.getTree());

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "and"


    public static class or_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "or"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:109:1: or : and ( '||' ^ and ) ;
    public final GateGrammarParser.or_return or() throws RecognitionException {
        GateGrammarParser.or_return retval = new GateGrammarParser.or_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal52=null;
        GateGrammarParser.and_return and51 =null;

        GateGrammarParser.and_return and53 =null;


        CommonTree string_literal52_tree=null;

        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:110:3: ( and ( '||' ^ and ) )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:110:5: and ( '||' ^ and )
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_and_in_or390);
            and51=and();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, and51.getTree());

            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:110:9: ( '||' ^ and )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:110:10: '||' ^ and
            {
            string_literal52=(Token)match(input,31,FOLLOW_31_in_or393); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal52_tree = 
            (CommonTree)adaptor.create(string_literal52)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal52_tree, root_0);
            }

            pushFollow(FOLLOW_and_in_or396);
            and53=and();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, and53.getTree());

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "or"


    public static class comparison_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "comparison"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:113:1: comparison : add ( '>' ^| '<' ^| '>=' ^| '<=' ^| '==' ^| '!=' ^) add ;
    public final GateGrammarParser.comparison_return comparison() throws RecognitionException {
        GateGrammarParser.comparison_return retval = new GateGrammarParser.comparison_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal55=null;
        Token char_literal56=null;
        Token string_literal57=null;
        Token string_literal58=null;
        Token string_literal59=null;
        Token string_literal60=null;
        GateGrammarParser.add_return add54 =null;

        GateGrammarParser.add_return add61 =null;


        CommonTree char_literal55_tree=null;
        CommonTree char_literal56_tree=null;
        CommonTree string_literal57_tree=null;
        CommonTree string_literal58_tree=null;
        CommonTree string_literal59_tree=null;
        CommonTree string_literal60_tree=null;

        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:114:3: ( add ( '>' ^| '<' ^| '>=' ^| '<=' ^| '==' ^| '!=' ^) add )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:114:5: add ( '>' ^| '<' ^| '>=' ^| '<=' ^| '==' ^| '!=' ^) add
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_add_in_comparison408);
            add54=add();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, add54.getTree());

            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:114:9: ( '>' ^| '<' ^| '>=' ^| '<=' ^| '==' ^| '!=' ^)
            int alt10=6;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt10=1;
                }
                break;
            case 24:
                {
                alt10=2;
                }
                break;
            case 28:
                {
                alt10=3;
                }
                break;
            case 25:
                {
                alt10=4;
                }
                break;
            case 26:
                {
                alt10=5;
                }
                break;
            case 15:
                {
                alt10=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }

            switch (alt10) {
                case 1 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:114:10: '>' ^
                    {
                    char_literal55=(Token)match(input,27,FOLLOW_27_in_comparison411); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal55_tree = 
                    (CommonTree)adaptor.create(char_literal55)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal55_tree, root_0);
                    }

                    }
                    break;
                case 2 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:114:17: '<' ^
                    {
                    char_literal56=(Token)match(input,24,FOLLOW_24_in_comparison416); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal56_tree = 
                    (CommonTree)adaptor.create(char_literal56)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal56_tree, root_0);
                    }

                    }
                    break;
                case 3 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:114:24: '>=' ^
                    {
                    string_literal57=(Token)match(input,28,FOLLOW_28_in_comparison421); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal57_tree = 
                    (CommonTree)adaptor.create(string_literal57)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal57_tree, root_0);
                    }

                    }
                    break;
                case 4 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:114:32: '<=' ^
                    {
                    string_literal58=(Token)match(input,25,FOLLOW_25_in_comparison426); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal58_tree = 
                    (CommonTree)adaptor.create(string_literal58)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal58_tree, root_0);
                    }

                    }
                    break;
                case 5 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:114:40: '==' ^
                    {
                    string_literal59=(Token)match(input,26,FOLLOW_26_in_comparison431); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal59_tree = 
                    (CommonTree)adaptor.create(string_literal59)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal59_tree, root_0);
                    }

                    }
                    break;
                case 6 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:114:49: '!=' ^
                    {
                    string_literal60=(Token)match(input,15,FOLLOW_15_in_comparison437); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal60_tree = 
                    (CommonTree)adaptor.create(string_literal60)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal60_tree, root_0);
                    }

                    }
                    break;

            }


            pushFollow(FOLLOW_add_in_comparison441);
            add61=add();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, add61.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "comparison"

    // $ANTLR start synpred4_GateGrammar
    public final void synpred4_GateGrammar_fragment() throws RecognitionException {
        // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:61:5: ( MATH '(' add ')' )
        // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:61:5: MATH '(' add ')'
        {
        match(input,MATH,FOLLOW_MATH_in_synpred4_GateGrammar115); if (state.failed) return ;

        match(input,17,FOLLOW_17_in_synpred4_GateGrammar118); if (state.failed) return ;

        pushFollow(FOLLOW_add_in_synpred4_GateGrammar121);
        add();

        state._fsp--;
        if (state.failed) return ;

        match(input,18,FOLLOW_18_in_synpred4_GateGrammar123); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred4_GateGrammar

    // $ANTLR start synpred5_GateGrammar
    public final void synpred5_GateGrammar_fragment() throws RecognitionException {
        // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:62:5: ( MATH '(' add ',' add ')' )
        // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:62:5: MATH '(' add ',' add ')'
        {
        match(input,MATH,FOLLOW_MATH_in_synpred5_GateGrammar132); if (state.failed) return ;

        match(input,17,FOLLOW_17_in_synpred5_GateGrammar135); if (state.failed) return ;

        pushFollow(FOLLOW_add_in_synpred5_GateGrammar138);
        add();

        state._fsp--;
        if (state.failed) return ;

        match(input,21,FOLLOW_21_in_synpred5_GateGrammar140); if (state.failed) return ;

        pushFollow(FOLLOW_add_in_synpred5_GateGrammar143);
        add();

        state._fsp--;
        if (state.failed) return ;

        match(input,18,FOLLOW_18_in_synpred5_GateGrammar145); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred5_GateGrammar

    // Delegated rules

    public final boolean synpred4_GateGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_GateGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_GateGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_GateGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_add_in_gateExpression77 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_gateExpression79 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_term91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOLTAGE_in_term97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_term103 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_add_in_term106 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_term108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATH_in_term115 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_term118 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_add_in_term121 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_term123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATH_in_term132 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_term135 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_add_in_term138 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_term140 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_add_in_term143 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_term145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ternary_in_term154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_unary173 = new BitSet(new long[]{0x0000000040021280L});
    public static final BitSet FOLLOW_negation_in_unary178 = new BitSet(new long[]{0x0000000040021280L});
    public static final BitSet FOLLOW_term_in_unary183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_negation195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_power208 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29_in_power211 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_unary_in_power214 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_power_in_mult229 = new BitSet(new long[]{0x0000000000880002L});
    public static final BitSet FOLLOW_19_in_mult234 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_23_in_mult239 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_power_in_mult243 = new BitSet(new long[]{0x0000000000880002L});
    public static final BitSet FOLLOW_mult_in_add258 = new BitSet(new long[]{0x0000000000500002L});
    public static final BitSet FOLLOW_20_in_add262 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_22_in_add267 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_mult_in_add271 = new BitSet(new long[]{0x0000000000500002L});
    public static final BitSet FOLLOW_30_in_ternary290 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ternary293 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_logical_in_ternary296 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ternary298 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_add_in_ternary301 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ternary303 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_add_in_ternary306 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ternary308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_logical326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_logical332 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_or_in_logical334 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_logical336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_not353 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_logical_in_not356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_in_and370 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_and373 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_not_in_and376 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_and_in_or390 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_or393 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_and_in_or396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_add_in_comparison408 = new BitSet(new long[]{0x000000001F008000L});
    public static final BitSet FOLLOW_27_in_comparison411 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_24_in_comparison416 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_28_in_comparison421 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_25_in_comparison426 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_26_in_comparison431 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_15_in_comparison437 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_add_in_comparison441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATH_in_synpred4_GateGrammar115 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_synpred4_GateGrammar118 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_add_in_synpred4_GateGrammar121 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_synpred4_GateGrammar123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATH_in_synpred5_GateGrammar132 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_synpred5_GateGrammar135 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_add_in_synpred5_GateGrammar138 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_synpred5_GateGrammar140 = new BitSet(new long[]{0x0000000040521280L});
    public static final BitSet FOLLOW_add_in_synpred5_GateGrammar143 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_synpred5_GateGrammar145 = new BitSet(new long[]{0x0000000000000002L});

}