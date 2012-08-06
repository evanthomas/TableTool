// $ANTLR 3.4 D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g 2012-08-06 13:41:33

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "DIGIT", "DOT", "EXPONENT", "FLOAT", "LETTER", "MATH", "NEGATION", "SIGN", "TIME", "VOLTAGE", "WS", "'!'", "'!='", "'&&'", "'('", "')'", "'*'", "'+'", "','", "'-'", "'/'", "'<'", "'<='", "'=='", "'>'", "'>='", "'^'", "'if'", "'||'"
    };

    public static final int EOF=-1;
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
    public static final int T__32=32;
    public static final int DIGIT=4;
    public static final int DOT=5;
    public static final int EXPONENT=6;
    public static final int FLOAT=7;
    public static final int LETTER=8;
    public static final int MATH=9;
    public static final int NEGATION=10;
    public static final int SIGN=11;
    public static final int TIME=12;
    public static final int VOLTAGE=13;
    public static final int WS=14;

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
    public String getGrammarFileName() { return "D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g"; }



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
    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:55:1: gateExpression : add EOF !;
    public final GateGrammarParser.gateExpression_return gateExpression() throws RecognitionException {
        GateGrammarParser.gateExpression_return retval = new GateGrammarParser.gateExpression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token EOF2=null;
        GateGrammarParser.add_return add1 =null;


        CommonTree EOF2_tree=null;

        try {
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:55:16: ( add EOF !)
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:55:18: add EOF !
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
    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:57:1: term : ( FLOAT | VOLTAGE | TIME | '(' ! add ')' !| MATH ^ '(' ! add ')' !| MATH ^ '(' ! add ',' ! add ')' !| ternary );
    public final GateGrammarParser.term_return term() throws RecognitionException {
        GateGrammarParser.term_return retval = new GateGrammarParser.term_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token FLOAT3=null;
        Token VOLTAGE4=null;
        Token TIME5=null;
        Token char_literal6=null;
        Token char_literal8=null;
        Token MATH9=null;
        Token char_literal10=null;
        Token char_literal12=null;
        Token MATH13=null;
        Token char_literal14=null;
        Token char_literal16=null;
        Token char_literal18=null;
        GateGrammarParser.add_return add7 =null;

        GateGrammarParser.add_return add11 =null;

        GateGrammarParser.add_return add15 =null;

        GateGrammarParser.add_return add17 =null;

        GateGrammarParser.ternary_return ternary19 =null;


        CommonTree FLOAT3_tree=null;
        CommonTree VOLTAGE4_tree=null;
        CommonTree TIME5_tree=null;
        CommonTree char_literal6_tree=null;
        CommonTree char_literal8_tree=null;
        CommonTree MATH9_tree=null;
        CommonTree char_literal10_tree=null;
        CommonTree char_literal12_tree=null;
        CommonTree MATH13_tree=null;
        CommonTree char_literal14_tree=null;
        CommonTree char_literal16_tree=null;
        CommonTree char_literal18_tree=null;

        try {
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:58:3: ( FLOAT | VOLTAGE | TIME | '(' ! add ')' !| MATH ^ '(' ! add ')' !| MATH ^ '(' ! add ',' ! add ')' !| ternary )
            int alt1=7;
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
            case TIME:
                {
                alt1=3;
                }
                break;
            case 18:
                {
                alt1=4;
                }
                break;
            case MATH:
                {
                int LA1_5 = input.LA(2);

                if ( (synpred5_GateGrammar()) ) {
                    alt1=5;
                }
                else if ( (synpred6_GateGrammar()) ) {
                    alt1=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 5, input);

                    throw nvae;

                }
                }
                break;
            case 31:
                {
                alt1=7;
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
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:58:5: FLOAT
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
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:59:5: VOLTAGE
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
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:60:5: TIME
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    TIME5=(Token)match(input,TIME,FOLLOW_TIME_in_term103); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TIME5_tree = 
                    (CommonTree)adaptor.create(TIME5)
                    ;
                    adaptor.addChild(root_0, TIME5_tree);
                    }

                    }
                    break;
                case 4 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:61:5: '(' ! add ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal6=(Token)match(input,18,FOLLOW_18_in_term109); if (state.failed) return retval;

                    pushFollow(FOLLOW_add_in_term112);
                    add7=add();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, add7.getTree());

                    char_literal8=(Token)match(input,19,FOLLOW_19_in_term114); if (state.failed) return retval;

                    }
                    break;
                case 5 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:62:5: MATH ^ '(' ! add ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    MATH9=(Token)match(input,MATH,FOLLOW_MATH_in_term121); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MATH9_tree = 
                    (CommonTree)adaptor.create(MATH9)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(MATH9_tree, root_0);
                    }

                    char_literal10=(Token)match(input,18,FOLLOW_18_in_term124); if (state.failed) return retval;

                    pushFollow(FOLLOW_add_in_term127);
                    add11=add();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, add11.getTree());

                    char_literal12=(Token)match(input,19,FOLLOW_19_in_term129); if (state.failed) return retval;

                    if ( state.backtracking==0 ) {checkSingleMath((MATH9!=null?MATH9.getText():null));}

                    }
                    break;
                case 6 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:63:5: MATH ^ '(' ! add ',' ! add ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    MATH13=(Token)match(input,MATH,FOLLOW_MATH_in_term138); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MATH13_tree = 
                    (CommonTree)adaptor.create(MATH13)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(MATH13_tree, root_0);
                    }

                    char_literal14=(Token)match(input,18,FOLLOW_18_in_term141); if (state.failed) return retval;

                    pushFollow(FOLLOW_add_in_term144);
                    add15=add();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, add15.getTree());

                    char_literal16=(Token)match(input,22,FOLLOW_22_in_term146); if (state.failed) return retval;

                    pushFollow(FOLLOW_add_in_term149);
                    add17=add();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, add17.getTree());

                    char_literal18=(Token)match(input,19,FOLLOW_19_in_term151); if (state.failed) return retval;

                    if ( state.backtracking==0 ) {checkDoubleMath((MATH13!=null?MATH13.getText():null));}

                    }
                    break;
                case 7 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:64:5: ternary
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_ternary_in_term160);
                    ternary19=ternary();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ternary19.getTree());

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
    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:67:1: unary : ( '+' !| negation ^)? term ;
    public final GateGrammarParser.unary_return unary() throws RecognitionException {
        GateGrammarParser.unary_return retval = new GateGrammarParser.unary_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal20=null;
        GateGrammarParser.negation_return negation21 =null;

        GateGrammarParser.term_return term22 =null;


        CommonTree char_literal20_tree=null;

        try {
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:68:3: ( ( '+' !| negation ^)? term )
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:69:3: ( '+' !| negation ^)? term
            {
            root_0 = (CommonTree)adaptor.nil();


            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:69:3: ( '+' !| negation ^)?
            int alt2=3;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==21) ) {
                alt2=1;
            }
            else if ( (LA2_0==23) ) {
                alt2=2;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:69:4: '+' !
                    {
                    char_literal20=(Token)match(input,21,FOLLOW_21_in_unary179); if (state.failed) return retval;

                    }
                    break;
                case 2 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:69:11: negation ^
                    {
                    pushFollow(FOLLOW_negation_in_unary184);
                    negation21=negation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (CommonTree)adaptor.becomeRoot(negation21.getTree(), root_0);

                    }
                    break;

            }


            pushFollow(FOLLOW_term_in_unary189);
            term22=term();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, term22.getTree());

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
    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:72:1: negation : '-' -> NEGATION ;
    public final GateGrammarParser.negation_return negation() throws RecognitionException {
        GateGrammarParser.negation_return retval = new GateGrammarParser.negation_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal23=null;

        CommonTree char_literal23_tree=null;
        RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");

        try {
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:73:1: ( '-' -> NEGATION )
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:74:1: '-'
            {
            char_literal23=(Token)match(input,23,FOLLOW_23_in_negation201); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_23.add(char_literal23);


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
            // 74:5: -> NEGATION
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
    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:77:1: power : unary ( '^' ^ unary )* ;
    public final GateGrammarParser.power_return power() throws RecognitionException {
        GateGrammarParser.power_return retval = new GateGrammarParser.power_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal25=null;
        GateGrammarParser.unary_return unary24 =null;

        GateGrammarParser.unary_return unary26 =null;


        CommonTree char_literal25_tree=null;

        try {
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:78:1: ( unary ( '^' ^ unary )* )
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:79:1: unary ( '^' ^ unary )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_unary_in_power214);
            unary24=unary();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unary24.getTree());

            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:79:7: ( '^' ^ unary )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==30) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:79:8: '^' ^ unary
            	    {
            	    char_literal25=(Token)match(input,30,FOLLOW_30_in_power217); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal25_tree = 
            	    (CommonTree)adaptor.create(char_literal25)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal25_tree, root_0);
            	    }

            	    pushFollow(FOLLOW_unary_in_power220);
            	    unary26=unary();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unary26.getTree());

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
    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:82:1: mult : power ( ( '*' ^| '/' ^) power )* ;
    public final GateGrammarParser.mult_return mult() throws RecognitionException {
        GateGrammarParser.mult_return retval = new GateGrammarParser.mult_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal28=null;
        Token char_literal29=null;
        GateGrammarParser.power_return power27 =null;

        GateGrammarParser.power_return power30 =null;


        CommonTree char_literal28_tree=null;
        CommonTree char_literal29_tree=null;

        try {
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:83:3: ( power ( ( '*' ^| '/' ^) power )* )
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:84:3: power ( ( '*' ^| '/' ^) power )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_power_in_mult235);
            power27=power();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, power27.getTree());

            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:84:10: ( ( '*' ^| '/' ^) power )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==20||LA5_0==24) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:84:11: ( '*' ^| '/' ^) power
            	    {
            	    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:84:11: ( '*' ^| '/' ^)
            	    int alt4=2;
            	    int LA4_0 = input.LA(1);

            	    if ( (LA4_0==20) ) {
            	        alt4=1;
            	    }
            	    else if ( (LA4_0==24) ) {
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
            	            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:84:12: '*' ^
            	            {
            	            char_literal28=(Token)match(input,20,FOLLOW_20_in_mult240); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal28_tree = 
            	            (CommonTree)adaptor.create(char_literal28)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal28_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:84:19: '/' ^
            	            {
            	            char_literal29=(Token)match(input,24,FOLLOW_24_in_mult245); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal29_tree = 
            	            (CommonTree)adaptor.create(char_literal29)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal29_tree, root_0);
            	            }

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_power_in_mult249);
            	    power30=power();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, power30.getTree());

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
    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:87:1: add : mult ( ( '+' ^| '-' ^) mult )* ;
    public final GateGrammarParser.add_return add() throws RecognitionException {
        GateGrammarParser.add_return retval = new GateGrammarParser.add_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal32=null;
        Token char_literal33=null;
        GateGrammarParser.mult_return mult31 =null;

        GateGrammarParser.mult_return mult34 =null;


        CommonTree char_literal32_tree=null;
        CommonTree char_literal33_tree=null;

        try {
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:88:3: ( mult ( ( '+' ^| '-' ^) mult )* )
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:89:3: mult ( ( '+' ^| '-' ^) mult )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_mult_in_add264);
            mult31=mult();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, mult31.getTree());

            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:89:8: ( ( '+' ^| '-' ^) mult )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==21||LA7_0==23) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:89:9: ( '+' ^| '-' ^) mult
            	    {
            	    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:89:9: ( '+' ^| '-' ^)
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0==21) ) {
            	        alt6=1;
            	    }
            	    else if ( (LA6_0==23) ) {
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
            	            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:89:10: '+' ^
            	            {
            	            char_literal32=(Token)match(input,21,FOLLOW_21_in_add268); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal32_tree = 
            	            (CommonTree)adaptor.create(char_literal32)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal32_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:89:17: '-' ^
            	            {
            	            char_literal33=(Token)match(input,23,FOLLOW_23_in_add273); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal33_tree = 
            	            (CommonTree)adaptor.create(char_literal33)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal33_tree, root_0);
            	            }

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_mult_in_add277);
            	    mult34=mult();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, mult34.getTree());

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
    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:92:1: ternary : 'if' ^ '(' ! or ',' ! add ',' ! add ')' !;
    public final GateGrammarParser.ternary_return ternary() throws RecognitionException {
        GateGrammarParser.ternary_return retval = new GateGrammarParser.ternary_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal35=null;
        Token char_literal36=null;
        Token char_literal38=null;
        Token char_literal40=null;
        Token char_literal42=null;
        GateGrammarParser.or_return or37 =null;

        GateGrammarParser.add_return add39 =null;

        GateGrammarParser.add_return add41 =null;


        CommonTree string_literal35_tree=null;
        CommonTree char_literal36_tree=null;
        CommonTree char_literal38_tree=null;
        CommonTree char_literal40_tree=null;
        CommonTree char_literal42_tree=null;

        try {
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:93:4: ( 'if' ^ '(' ! or ',' ! add ',' ! add ')' !)
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:94:4: 'if' ^ '(' ! or ',' ! add ',' ! add ')' !
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal35=(Token)match(input,31,FOLLOW_31_in_ternary296); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal35_tree = 
            (CommonTree)adaptor.create(string_literal35)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal35_tree, root_0);
            }

            char_literal36=(Token)match(input,18,FOLLOW_18_in_ternary299); if (state.failed) return retval;

            pushFollow(FOLLOW_or_in_ternary302);
            or37=or();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, or37.getTree());

            char_literal38=(Token)match(input,22,FOLLOW_22_in_ternary304); if (state.failed) return retval;

            pushFollow(FOLLOW_add_in_ternary307);
            add39=add();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, add39.getTree());

            char_literal40=(Token)match(input,22,FOLLOW_22_in_ternary309); if (state.failed) return retval;

            pushFollow(FOLLOW_add_in_ternary312);
            add41=add();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, add41.getTree());

            char_literal42=(Token)match(input,19,FOLLOW_19_in_ternary314); if (state.failed) return retval;

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
    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:97:1: logical : ( comparison | '(' ! or ')' !);
    public final GateGrammarParser.logical_return logical() throws RecognitionException {
        GateGrammarParser.logical_return retval = new GateGrammarParser.logical_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal44=null;
        Token char_literal46=null;
        GateGrammarParser.comparison_return comparison43 =null;

        GateGrammarParser.or_return or45 =null;


        CommonTree char_literal44_tree=null;
        CommonTree char_literal46_tree=null;

        try {
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:98:3: ( comparison | '(' ! or ')' !)
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==FLOAT||LA8_0==MATH||(LA8_0 >= TIME && LA8_0 <= VOLTAGE)||LA8_0==21||LA8_0==23||LA8_0==31) ) {
                alt8=1;
            }
            else if ( (LA8_0==18) ) {
                int LA8_6 = input.LA(2);

                if ( (synpred14_GateGrammar()) ) {
                    alt8=1;
                }
                else if ( (true) ) {
                    alt8=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 6, input);

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
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:98:5: comparison
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_comparison_in_logical332);
                    comparison43=comparison();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comparison43.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:99:5: '(' ! or ')' !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal44=(Token)match(input,18,FOLLOW_18_in_logical339); if (state.failed) return retval;

                    pushFollow(FOLLOW_or_in_logical342);
                    or45=or();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, or45.getTree());

                    char_literal46=(Token)match(input,19,FOLLOW_19_in_logical344); if (state.failed) return retval;

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
    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:102:2: not : ( '!' ^)? comparison ;
    public final GateGrammarParser.not_return not() throws RecognitionException {
        GateGrammarParser.not_return retval = new GateGrammarParser.not_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal47=null;
        GateGrammarParser.comparison_return comparison48 =null;


        CommonTree char_literal47_tree=null;

        try {
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:103:4: ( ( '!' ^)? comparison )
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:103:6: ( '!' ^)? comparison
            {
            root_0 = (CommonTree)adaptor.nil();


            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:103:6: ( '!' ^)?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==15) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:103:7: '!' ^
                    {
                    char_literal47=(Token)match(input,15,FOLLOW_15_in_not364); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal47_tree = 
                    (CommonTree)adaptor.create(char_literal47)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal47_tree, root_0);
                    }

                    }
                    break;

            }


            pushFollow(FOLLOW_comparison_in_not369);
            comparison48=comparison();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, comparison48.getTree());

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
    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:106:1: and : logical ( '&&' ^ logical )* ;
    public final GateGrammarParser.and_return and() throws RecognitionException {
        GateGrammarParser.and_return retval = new GateGrammarParser.and_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal50=null;
        GateGrammarParser.logical_return logical49 =null;

        GateGrammarParser.logical_return logical51 =null;


        CommonTree string_literal50_tree=null;

        try {
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:107:3: ( logical ( '&&' ^ logical )* )
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:107:5: logical ( '&&' ^ logical )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_logical_in_and381);
            logical49=logical();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, logical49.getTree());

            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:107:13: ( '&&' ^ logical )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==17) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:107:14: '&&' ^ logical
            	    {
            	    string_literal50=(Token)match(input,17,FOLLOW_17_in_and384); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal50_tree = 
            	    (CommonTree)adaptor.create(string_literal50)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(string_literal50_tree, root_0);
            	    }

            	    pushFollow(FOLLOW_logical_in_and387);
            	    logical51=logical();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, logical51.getTree());

            	    }
            	    break;

            	default :
            	    break loop10;
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
    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:110:1: or : and ( '||' ^ and )* ;
    public final GateGrammarParser.or_return or() throws RecognitionException {
        GateGrammarParser.or_return retval = new GateGrammarParser.or_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal53=null;
        GateGrammarParser.and_return and52 =null;

        GateGrammarParser.and_return and54 =null;


        CommonTree string_literal53_tree=null;

        try {
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:111:3: ( and ( '||' ^ and )* )
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:111:5: and ( '||' ^ and )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_and_in_or400);
            and52=and();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, and52.getTree());

            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:111:9: ( '||' ^ and )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==32) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:111:10: '||' ^ and
            	    {
            	    string_literal53=(Token)match(input,32,FOLLOW_32_in_or403); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal53_tree = 
            	    (CommonTree)adaptor.create(string_literal53)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(string_literal53_tree, root_0);
            	    }

            	    pushFollow(FOLLOW_and_in_or406);
            	    and54=and();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, and54.getTree());

            	    }
            	    break;

            	default :
            	    break loop11;
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
    // $ANTLR end "or"


    public static class comparison_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "comparison"
    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:114:1: comparison : add ( '>' ^| '<' ^| '>=' ^| '<=' ^| '==' ^| '!=' ^) add ;
    public final GateGrammarParser.comparison_return comparison() throws RecognitionException {
        GateGrammarParser.comparison_return retval = new GateGrammarParser.comparison_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal56=null;
        Token char_literal57=null;
        Token string_literal58=null;
        Token string_literal59=null;
        Token string_literal60=null;
        Token string_literal61=null;
        GateGrammarParser.add_return add55 =null;

        GateGrammarParser.add_return add62 =null;


        CommonTree char_literal56_tree=null;
        CommonTree char_literal57_tree=null;
        CommonTree string_literal58_tree=null;
        CommonTree string_literal59_tree=null;
        CommonTree string_literal60_tree=null;
        CommonTree string_literal61_tree=null;

        try {
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:115:3: ( add ( '>' ^| '<' ^| '>=' ^| '<=' ^| '==' ^| '!=' ^) add )
            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:115:5: add ( '>' ^| '<' ^| '>=' ^| '<=' ^| '==' ^| '!=' ^) add
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_add_in_comparison419);
            add55=add();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, add55.getTree());

            // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:115:9: ( '>' ^| '<' ^| '>=' ^| '<=' ^| '==' ^| '!=' ^)
            int alt12=6;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt12=1;
                }
                break;
            case 25:
                {
                alt12=2;
                }
                break;
            case 29:
                {
                alt12=3;
                }
                break;
            case 26:
                {
                alt12=4;
                }
                break;
            case 27:
                {
                alt12=5;
                }
                break;
            case 16:
                {
                alt12=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }

            switch (alt12) {
                case 1 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:115:10: '>' ^
                    {
                    char_literal56=(Token)match(input,28,FOLLOW_28_in_comparison422); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal56_tree = 
                    (CommonTree)adaptor.create(char_literal56)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal56_tree, root_0);
                    }

                    }
                    break;
                case 2 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:115:17: '<' ^
                    {
                    char_literal57=(Token)match(input,25,FOLLOW_25_in_comparison427); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal57_tree = 
                    (CommonTree)adaptor.create(char_literal57)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal57_tree, root_0);
                    }

                    }
                    break;
                case 3 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:115:24: '>=' ^
                    {
                    string_literal58=(Token)match(input,29,FOLLOW_29_in_comparison432); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal58_tree = 
                    (CommonTree)adaptor.create(string_literal58)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal58_tree, root_0);
                    }

                    }
                    break;
                case 4 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:115:32: '<=' ^
                    {
                    string_literal59=(Token)match(input,26,FOLLOW_26_in_comparison437); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal59_tree = 
                    (CommonTree)adaptor.create(string_literal59)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal59_tree, root_0);
                    }

                    }
                    break;
                case 5 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:115:40: '==' ^
                    {
                    string_literal60=(Token)match(input,27,FOLLOW_27_in_comparison442); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal60_tree = 
                    (CommonTree)adaptor.create(string_literal60)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal60_tree, root_0);
                    }

                    }
                    break;
                case 6 :
                    // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:115:49: '!=' ^
                    {
                    string_literal61=(Token)match(input,16,FOLLOW_16_in_comparison448); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal61_tree = 
                    (CommonTree)adaptor.create(string_literal61)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal61_tree, root_0);
                    }

                    }
                    break;

            }


            pushFollow(FOLLOW_add_in_comparison452);
            add62=add();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, add62.getTree());

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

    // $ANTLR start synpred5_GateGrammar
    public final void synpred5_GateGrammar_fragment() throws RecognitionException {
        // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:62:5: ( MATH '(' add ')' )
        // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:62:5: MATH '(' add ')'
        {
        match(input,MATH,FOLLOW_MATH_in_synpred5_GateGrammar121); if (state.failed) return ;

        match(input,18,FOLLOW_18_in_synpred5_GateGrammar124); if (state.failed) return ;

        pushFollow(FOLLOW_add_in_synpred5_GateGrammar127);
        add();

        state._fsp--;
        if (state.failed) return ;

        match(input,19,FOLLOW_19_in_synpred5_GateGrammar129); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred5_GateGrammar

    // $ANTLR start synpred6_GateGrammar
    public final void synpred6_GateGrammar_fragment() throws RecognitionException {
        // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:63:5: ( MATH '(' add ',' add ')' )
        // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:63:5: MATH '(' add ',' add ')'
        {
        match(input,MATH,FOLLOW_MATH_in_synpred6_GateGrammar138); if (state.failed) return ;

        match(input,18,FOLLOW_18_in_synpred6_GateGrammar141); if (state.failed) return ;

        pushFollow(FOLLOW_add_in_synpred6_GateGrammar144);
        add();

        state._fsp--;
        if (state.failed) return ;

        match(input,22,FOLLOW_22_in_synpred6_GateGrammar146); if (state.failed) return ;

        pushFollow(FOLLOW_add_in_synpred6_GateGrammar149);
        add();

        state._fsp--;
        if (state.failed) return ;

        match(input,19,FOLLOW_19_in_synpred6_GateGrammar151); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred6_GateGrammar

    // $ANTLR start synpred14_GateGrammar
    public final void synpred14_GateGrammar_fragment() throws RecognitionException {
        // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:98:5: ( comparison )
        // D:\\Stuff\\Dropbox\\eclipse\\TableTool\\src\\expressionEvaluator\\GateGrammar.g:98:5: comparison
        {
        pushFollow(FOLLOW_comparison_in_synpred14_GateGrammar332);
        comparison();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred14_GateGrammar

    // Delegated rules

    public final boolean synpred6_GateGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_GateGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_GateGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_GateGrammar_fragment(); // can never throw exception
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
    public static final BitSet FOLLOW_TIME_in_term103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_term109 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_add_in_term112 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_term114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATH_in_term121 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_term124 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_add_in_term127 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_term129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATH_in_term138 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_term141 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_add_in_term144 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_term146 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_add_in_term149 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_term151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ternary_in_term160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_unary179 = new BitSet(new long[]{0x0000000080043280L});
    public static final BitSet FOLLOW_negation_in_unary184 = new BitSet(new long[]{0x0000000080043280L});
    public static final BitSet FOLLOW_term_in_unary189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_negation201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_power214 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_power217 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_unary_in_power220 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_power_in_mult235 = new BitSet(new long[]{0x0000000001100002L});
    public static final BitSet FOLLOW_20_in_mult240 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_24_in_mult245 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_power_in_mult249 = new BitSet(new long[]{0x0000000001100002L});
    public static final BitSet FOLLOW_mult_in_add264 = new BitSet(new long[]{0x0000000000A00002L});
    public static final BitSet FOLLOW_21_in_add268 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_23_in_add273 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_mult_in_add277 = new BitSet(new long[]{0x0000000000A00002L});
    public static final BitSet FOLLOW_31_in_ternary296 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ternary299 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_or_in_ternary302 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ternary304 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_add_in_ternary307 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ternary309 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_add_in_ternary312 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ternary314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_logical332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_logical339 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_or_in_logical342 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_logical344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_not364 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_comparison_in_not369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_logical_in_and381 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_and384 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_logical_in_and387 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_and_in_or400 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_32_in_or403 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_and_in_or406 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_add_in_comparison419 = new BitSet(new long[]{0x000000003E010000L});
    public static final BitSet FOLLOW_28_in_comparison422 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_25_in_comparison427 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_29_in_comparison432 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_26_in_comparison437 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_27_in_comparison442 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_16_in_comparison448 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_add_in_comparison452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATH_in_synpred5_GateGrammar121 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_synpred5_GateGrammar124 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_add_in_synpred5_GateGrammar127 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_synpred5_GateGrammar129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MATH_in_synpred6_GateGrammar138 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_synpred6_GateGrammar141 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_add_in_synpred6_GateGrammar144 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_synpred6_GateGrammar146 = new BitSet(new long[]{0x0000000080A43280L});
    public static final BitSet FOLLOW_add_in_synpred6_GateGrammar149 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_synpred6_GateGrammar151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_synpred14_GateGrammar332 = new BitSet(new long[]{0x0000000000000002L});

}