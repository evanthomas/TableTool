// $ANTLR 3.4 D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g 2012-07-24 14:02:57

package expressionEvaluator;
import java.lang.Math;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class GateFunctionEvaluator extends TreeParser {
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
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators


    public GateFunctionEvaluator(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public GateFunctionEvaluator(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return GateFunctionEvaluator.tokenNames; }
    public String getGrammarFileName() { return "D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g"; }



    private double v;

    public void setV(double v) { this.v = v; }

    private double math(String fn, double op1, double op2) {
    if( fn.equals("exp") ) return Math.exp(op1);
    if( fn.equals("sin") ) return Math.sin(op1);
    if( fn.equals("cos") ) return Math.cos(op1);
    if( fn.equals("tan") ) return Math.tan(op1);
    if( fn.equals("abs") ) return Math.abs(op1);
    if( fn.equals("min") ) return Math.min(op1, op2);
    if( fn.equals("max") ) return Math.max(op1, op2);
    return 0; // Should never get here
    }



    // $ANTLR start "gateFunction"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:32:1: gateFunction returns [double result] : e= expression EOF ;
    public final double gateFunction() throws RecognitionException {
        double result = 0.0;


        double e =0.0;


        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:33:3: (e= expression EOF )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:33:3: e= expression EOF
            {
            pushFollow(FOLLOW_expression_in_gateFunction68);
            e=expression();

            state._fsp--;


            match(input,EOF,FOLLOW_EOF_in_gateFunction70); 

             result=e; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "gateFunction"



    // $ANTLR start "expression"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:36:1: expression returns [double result] : ( ^( '+' op1= expression op2= expression ) | ^( '-' op1= expression op2= expression ) | ^( '*' op1= expression op2= expression ) | ^( '/' op1= expression op2= expression ) | ^( '^' op1= expression op2= expression ) | ^( NEGATION op= expression ) | ^( MATH op1= expression (op2= expression )? ) | ^( 'if' t= logical op1= expression op2= expression ) | FLOAT | VOLTAGE );
    public final double expression() throws RecognitionException {
        double result = 0.0;


        CommonTree MATH1=null;
        CommonTree FLOAT2=null;
        double op1 =0.0;

        double op2 =0.0;

        double op =0.0;

        boolean t =false;


        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:37:3: ( ^( '+' op1= expression op2= expression ) | ^( '-' op1= expression op2= expression ) | ^( '*' op1= expression op2= expression ) | ^( '/' op1= expression op2= expression ) | ^( '^' op1= expression op2= expression ) | ^( NEGATION op= expression ) | ^( MATH op1= expression (op2= expression )? ) | ^( 'if' t= logical op1= expression op2= expression ) | FLOAT | VOLTAGE )
            int alt2=10;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt2=1;
                }
                break;
            case 22:
                {
                alt2=2;
                }
                break;
            case 19:
                {
                alt2=3;
                }
                break;
            case 23:
                {
                alt2=4;
                }
                break;
            case 29:
                {
                alt2=5;
                }
                break;
            case NEGATION:
                {
                alt2=6;
                }
                break;
            case MATH:
                {
                alt2=7;
                }
                break;
            case 30:
                {
                alt2=8;
                }
                break;
            case FLOAT:
                {
                alt2=9;
                }
                break;
            case VOLTAGE:
                {
                alt2=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:37:3: ^( '+' op1= expression op2= expression )
                    {
                    match(input,20,FOLLOW_20_in_expression86); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression90);
                    op1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression94);
                    op2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     result = op1+op2; 

                    }
                    break;
                case 2 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:38:3: ^( '-' op1= expression op2= expression )
                    {
                    match(input,22,FOLLOW_22_in_expression102); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression106);
                    op1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression110);
                    op2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     result = op1-op2; 

                    }
                    break;
                case 3 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:39:3: ^( '*' op1= expression op2= expression )
                    {
                    match(input,19,FOLLOW_19_in_expression118); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression122);
                    op1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression126);
                    op2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     result = op1*op2; 

                    }
                    break;
                case 4 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:40:3: ^( '/' op1= expression op2= expression )
                    {
                    match(input,23,FOLLOW_23_in_expression134); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression138);
                    op1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression142);
                    op2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     result = op1/op2; 

                    }
                    break;
                case 5 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:41:3: ^( '^' op1= expression op2= expression )
                    {
                    match(input,29,FOLLOW_29_in_expression150); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression154);
                    op1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression158);
                    op2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     result = Math.pow(op1,op2); 

                    }
                    break;
                case 6 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:42:3: ^( NEGATION op= expression )
                    {
                    match(input,NEGATION,FOLLOW_NEGATION_in_expression166); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression170);
                    op=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     result = -op; 

                    }
                    break;
                case 7 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:43:3: ^( MATH op1= expression (op2= expression )? )
                    {
                    MATH1=(CommonTree)match(input,MATH,FOLLOW_MATH_in_expression178); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression182);
                    op1=expression();

                    state._fsp--;


                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:43:25: (op2= expression )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==FLOAT||(LA1_0 >= MATH && LA1_0 <= NEGATION)||LA1_0==VOLTAGE||(LA1_0 >= 19 && LA1_0 <= 20)||(LA1_0 >= 22 && LA1_0 <= 23)||(LA1_0 >= 29 && LA1_0 <= 30)) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:43:26: op2= expression
                            {
                            pushFollow(FOLLOW_expression_in_expression187);
                            op2=expression();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 


                     result = math((MATH1!=null?MATH1.getText():null), op1, op2); 

                    }
                    break;
                case 8 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:44:3: ^( 'if' t= logical op1= expression op2= expression )
                    {
                    match(input,30,FOLLOW_30_in_expression197); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_logical_in_expression201);
                    t=logical();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression205);
                    op1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression209);
                    op2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     result = t?op1:op2; 

                    }
                    break;
                case 9 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:45:3: FLOAT
                    {
                    FLOAT2=(CommonTree)match(input,FLOAT,FOLLOW_FLOAT_in_expression216); 

                     result = Double.parseDouble((FLOAT2!=null?FLOAT2.getText():null)); 

                    }
                    break;
                case 10 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:46:3: VOLTAGE
                    {
                    match(input,VOLTAGE,FOLLOW_VOLTAGE_in_expression222); 

                     result = v;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "expression"



    // $ANTLR start "logical"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:49:1: logical returns [boolean truth] : ( ^( '!' op= logical ) | ^( '&&' op1= logical op2= logical ) | ^( '||' op1= logical op2= logical ) | (t= comparison ) );
    public final boolean logical() throws RecognitionException {
        boolean truth = false;


        boolean op =false;

        boolean op1 =false;

        boolean op2 =false;

        boolean t =false;


        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:50:3: ( ^( '!' op= logical ) | ^( '&&' op1= logical op2= logical ) | ^( '||' op1= logical op2= logical ) | (t= comparison ) )
            int alt3=4;
            switch ( input.LA(1) ) {
            case 14:
                {
                alt3=1;
                }
                break;
            case 16:
                {
                alt3=2;
                }
                break;
            case 31:
                {
                alt3=3;
                }
                break;
            case 15:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
                {
                alt3=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:50:3: ^( '!' op= logical )
                    {
                    match(input,14,FOLLOW_14_in_logical238); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_logical_in_logical242);
                    op=logical();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     truth = !op; 

                    }
                    break;
                case 2 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:51:3: ^( '&&' op1= logical op2= logical )
                    {
                    match(input,16,FOLLOW_16_in_logical250); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_logical_in_logical254);
                    op1=logical();

                    state._fsp--;


                    pushFollow(FOLLOW_logical_in_logical258);
                    op2=logical();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     truth = op1&&op2; 

                    }
                    break;
                case 3 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:52:3: ^( '||' op1= logical op2= logical )
                    {
                    match(input,31,FOLLOW_31_in_logical266); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_logical_in_logical270);
                    op1=logical();

                    state._fsp--;


                    pushFollow(FOLLOW_logical_in_logical274);
                    op2=logical();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     truth = op1||op2; 

                    }
                    break;
                case 4 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:53:3: (t= comparison )
                    {
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:53:3: (t= comparison )
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:53:4: t= comparison
                    {
                    pushFollow(FOLLOW_comparison_in_logical284);
                    t=comparison();

                    state._fsp--;


                    }


                     truth = t; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return truth;
    }
    // $ANTLR end "logical"



    // $ANTLR start "comparison"
    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:56:1: comparison returns [boolean truth] : ( ^( '>' op1= expression op2= expression ) | ^( '<' op1= expression op2= expression ) | ^( '>=' op1= expression op2= expression ) | ^( '<=' op1= expression op2= expression ) | ^( '==' op1= expression op2= expression ) | ^( '!=' op1= expression op2= expression ) );
    public final boolean comparison() throws RecognitionException {
        boolean truth = false;


        double op1 =0.0;

        double op2 =0.0;


        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:57:3: ( ^( '>' op1= expression op2= expression ) | ^( '<' op1= expression op2= expression ) | ^( '>=' op1= expression op2= expression ) | ^( '<=' op1= expression op2= expression ) | ^( '==' op1= expression op2= expression ) | ^( '!=' op1= expression op2= expression ) )
            int alt4=6;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt4=1;
                }
                break;
            case 24:
                {
                alt4=2;
                }
                break;
            case 28:
                {
                alt4=3;
                }
                break;
            case 25:
                {
                alt4=4;
                }
                break;
            case 26:
                {
                alt4=5;
                }
                break;
            case 15:
                {
                alt4=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:57:3: ^( '>' op1= expression op2= expression )
                    {
                    match(input,27,FOLLOW_27_in_comparison301); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_comparison306);
                    op1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_comparison310);
                    op2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     truth = op1>op2; 

                    }
                    break;
                case 2 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:58:3: ^( '<' op1= expression op2= expression )
                    {
                    match(input,24,FOLLOW_24_in_comparison318); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_comparison323);
                    op1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_comparison327);
                    op2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     truth = op1<op2; 

                    }
                    break;
                case 3 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:59:3: ^( '>=' op1= expression op2= expression )
                    {
                    match(input,28,FOLLOW_28_in_comparison335); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_comparison339);
                    op1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_comparison343);
                    op2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     truth = op1>=op2; 

                    }
                    break;
                case 4 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:60:3: ^( '<=' op1= expression op2= expression )
                    {
                    match(input,25,FOLLOW_25_in_comparison351); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_comparison355);
                    op1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_comparison359);
                    op2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     truth = op1<=op2; 

                    }
                    break;
                case 5 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:61:3: ^( '==' op1= expression op2= expression )
                    {
                    match(input,26,FOLLOW_26_in_comparison367); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_comparison371);
                    op1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_comparison375);
                    op2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     truth = op1==op2; 

                    }
                    break;
                case 6 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateFunctionEvaluator.g:62:3: ^( '!=' op1= expression op2= expression )
                    {
                    match(input,15,FOLLOW_15_in_comparison383); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_comparison387);
                    op1=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_comparison391);
                    op2=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     truth = op1!=op2; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return truth;
    }
    // $ANTLR end "comparison"

    // Delegated rules


 

    public static final BitSet FOLLOW_expression_in_gateFunction68 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_gateFunction70 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_expression86 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression90 = new BitSet(new long[]{0x0000000060D81680L});
    public static final BitSet FOLLOW_expression_in_expression94 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_22_in_expression102 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression106 = new BitSet(new long[]{0x0000000060D81680L});
    public static final BitSet FOLLOW_expression_in_expression110 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_19_in_expression118 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression122 = new BitSet(new long[]{0x0000000060D81680L});
    public static final BitSet FOLLOW_expression_in_expression126 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_23_in_expression134 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression138 = new BitSet(new long[]{0x0000000060D81680L});
    public static final BitSet FOLLOW_expression_in_expression142 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_29_in_expression150 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression154 = new BitSet(new long[]{0x0000000060D81680L});
    public static final BitSet FOLLOW_expression_in_expression158 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEGATION_in_expression166 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression170 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MATH_in_expression178 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression182 = new BitSet(new long[]{0x0000000060D81688L});
    public static final BitSet FOLLOW_expression_in_expression187 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_30_in_expression197 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_logical_in_expression201 = new BitSet(new long[]{0x0000000060D81680L});
    public static final BitSet FOLLOW_expression_in_expression205 = new BitSet(new long[]{0x0000000060D81680L});
    public static final BitSet FOLLOW_expression_in_expression209 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FLOAT_in_expression216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOLTAGE_in_expression222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_logical238 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_logical_in_logical242 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_16_in_logical250 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_logical_in_logical254 = new BitSet(new long[]{0x000000009F01C000L});
    public static final BitSet FOLLOW_logical_in_logical258 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_31_in_logical266 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_logical_in_logical270 = new BitSet(new long[]{0x000000009F01C000L});
    public static final BitSet FOLLOW_logical_in_logical274 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_comparison_in_logical284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_comparison301 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_comparison306 = new BitSet(new long[]{0x0000000060D81680L});
    public static final BitSet FOLLOW_expression_in_comparison310 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_24_in_comparison318 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_comparison323 = new BitSet(new long[]{0x0000000060D81680L});
    public static final BitSet FOLLOW_expression_in_comparison327 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_28_in_comparison335 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_comparison339 = new BitSet(new long[]{0x0000000060D81680L});
    public static final BitSet FOLLOW_expression_in_comparison343 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_25_in_comparison351 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_comparison355 = new BitSet(new long[]{0x0000000060D81680L});
    public static final BitSet FOLLOW_expression_in_comparison359 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_26_in_comparison367 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_comparison371 = new BitSet(new long[]{0x0000000060D81680L});
    public static final BitSet FOLLOW_expression_in_comparison375 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_15_in_comparison383 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_comparison387 = new BitSet(new long[]{0x0000000060D81680L});
    public static final BitSet FOLLOW_expression_in_comparison391 = new BitSet(new long[]{0x0000000000000008L});

}