// $ANTLR 3.4 D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g 2012-07-24 14:02:57

package expressionEvaluator;

import java.util.LinkedList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class GateGrammarLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public GateGrammarLexer() {} 
    public GateGrammarLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public GateGrammarLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g"; }

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:13:7: ( '!' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:13:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:14:7: ( '!=' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:14:9: '!='
            {
            match("!="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:15:7: ( '&&' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:15:9: '&&'
            {
            match("&&"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:16:7: ( '(' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:16:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:17:7: ( ')' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:17:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:18:7: ( '*' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:18:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:19:7: ( '+' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:19:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:20:7: ( ',' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:20:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:21:7: ( '-' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:21:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:22:7: ( '/' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:22:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:23:7: ( '<' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:23:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:24:7: ( '<=' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:24:9: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:25:7: ( '==' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:25:9: '=='
            {
            match("=="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:26:7: ( '>' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:26:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:27:7: ( '>=' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:27:9: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:28:7: ( '^' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:28:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:29:7: ( 'if' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:29:9: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:30:7: ( '||' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:30:9: '||'
            {
            match("||"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "VOLTAGE"
    public final void mVOLTAGE() throws RecognitionException {
        try {
            int _type = VOLTAGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:120:3: ( ( 'v' | 'V' ) )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:
            {
            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VOLTAGE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:128:3: ( ( ' ' | '\\n' | '\\r' | '\\f' | '\\t' )+ )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:129:3: ( ' ' | '\\n' | '\\r' | '\\f' | '\\t' )+
            {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:129:3: ( ' ' | '\\n' | '\\r' | '\\f' | '\\t' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '\t' && LA1_0 <= '\n')||(LA1_0 >= '\f' && LA1_0 <= '\r')||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:
            	    {
            	    if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);



                _channel = HIDDEN;
               

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:145:3: ( ( '0' .. '9' ) )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:151:3: ( '.' )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:152:3: '.'
            {
            match('.'); 

            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:157:3: ( ( 'e' | 'E' ) )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "SIGN"
    public final void mSIGN() throws RecognitionException {
        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:166:3: ( ( '+' | '-' ) )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:
            {
            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIGN"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:173:3: ( ( DIGIT )* ( DOT ( DIGIT )* )? ( EXPONENT ( SIGN )? ( DIGIT )+ )? )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:174:3: ( DIGIT )* ( DOT ( DIGIT )* )? ( EXPONENT ( SIGN )? ( DIGIT )+ )?
            {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:174:3: ( DIGIT )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:174:10: ( DOT ( DIGIT )* )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='.') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:174:11: DOT ( DIGIT )*
                    {
                    mDOT(); 


                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:174:15: ( DIGIT )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }
                    break;

            }


            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:174:24: ( EXPONENT ( SIGN )? ( DIGIT )+ )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='E'||LA7_0=='e') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:174:25: EXPONENT ( SIGN )? ( DIGIT )+
                    {
                    mEXPONENT(); 


                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:174:34: ( SIGN )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0=='+'||LA5_0=='-') ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:
                            {
                            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:174:40: ( DIGIT )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt6 >= 1 ) break loop6;
                                EarlyExitException eee =
                                    new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:177:17: ( ( 'a' .. 'z' ) )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:
            {
            if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "MATH"
    public final void mMATH() throws RecognitionException {
        try {
            int _type = MATH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:178:6: ( ( LETTER )+ )
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:178:8: ( LETTER )+
            {
            // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:178:8: ( LETTER )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= 'a' && LA8_0 <= 'z')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:
            	    {
            	    if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MATH"

    public void mTokens() throws RecognitionException {
        // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:8: ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | VOLTAGE | WS | FLOAT | MATH )
        int alt9=22;
        switch ( input.LA(1) ) {
        case '!':
            {
            int LA9_1 = input.LA(2);

            if ( (LA9_1=='=') ) {
                alt9=2;
            }
            else {
                alt9=1;
            }
            }
            break;
        case '&':
            {
            alt9=3;
            }
            break;
        case '(':
            {
            alt9=4;
            }
            break;
        case ')':
            {
            alt9=5;
            }
            break;
        case '*':
            {
            alt9=6;
            }
            break;
        case '+':
            {
            alt9=7;
            }
            break;
        case ',':
            {
            alt9=8;
            }
            break;
        case '-':
            {
            alt9=9;
            }
            break;
        case '/':
            {
            alt9=10;
            }
            break;
        case '<':
            {
            int LA9_10 = input.LA(2);

            if ( (LA9_10=='=') ) {
                alt9=12;
            }
            else {
                alt9=11;
            }
            }
            break;
        case '=':
            {
            alt9=13;
            }
            break;
        case '>':
            {
            int LA9_12 = input.LA(2);

            if ( (LA9_12=='=') ) {
                alt9=15;
            }
            else {
                alt9=14;
            }
            }
            break;
        case '^':
            {
            alt9=16;
            }
            break;
        case 'i':
            {
            int LA9_14 = input.LA(2);

            if ( (LA9_14=='f') ) {
                int LA9_28 = input.LA(3);

                if ( ((LA9_28 >= 'a' && LA9_28 <= 'z')) ) {
                    alt9=22;
                }
                else {
                    alt9=17;
                }
            }
            else {
                alt9=22;
            }
            }
            break;
        case '|':
            {
            alt9=18;
            }
            break;
        case 'v':
            {
            int LA9_16 = input.LA(2);

            if ( ((LA9_16 >= 'a' && LA9_16 <= 'z')) ) {
                alt9=22;
            }
            else {
                alt9=19;
            }
            }
            break;
        case '\t':
        case '\n':
        case '\f':
        case '\r':
        case ' ':
            {
            alt9=20;
            }
            break;
        case 'e':
            {
            int LA9_19 = input.LA(2);

            if ( (LA9_19=='+'||LA9_19=='-'||(LA9_19 >= '0' && LA9_19 <= '9')) ) {
                alt9=21;
            }
            else {
                alt9=22;
            }
            }
            break;
        case 'V':
            {
            alt9=19;
            }
            break;
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'f':
        case 'g':
        case 'h':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'o':
        case 'p':
        case 'q':
        case 'r':
        case 's':
        case 't':
        case 'u':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt9=22;
            }
            break;
        default:
            alt9=21;
        }

        switch (alt9) {
            case 1 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:10: T__14
                {
                mT__14(); 


                }
                break;
            case 2 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:16: T__15
                {
                mT__15(); 


                }
                break;
            case 3 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:22: T__16
                {
                mT__16(); 


                }
                break;
            case 4 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:28: T__17
                {
                mT__17(); 


                }
                break;
            case 5 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:34: T__18
                {
                mT__18(); 


                }
                break;
            case 6 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:40: T__19
                {
                mT__19(); 


                }
                break;
            case 7 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:46: T__20
                {
                mT__20(); 


                }
                break;
            case 8 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:52: T__21
                {
                mT__21(); 


                }
                break;
            case 9 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:58: T__22
                {
                mT__22(); 


                }
                break;
            case 10 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:64: T__23
                {
                mT__23(); 


                }
                break;
            case 11 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:70: T__24
                {
                mT__24(); 


                }
                break;
            case 12 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:76: T__25
                {
                mT__25(); 


                }
                break;
            case 13 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:82: T__26
                {
                mT__26(); 


                }
                break;
            case 14 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:88: T__27
                {
                mT__27(); 


                }
                break;
            case 15 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:94: T__28
                {
                mT__28(); 


                }
                break;
            case 16 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:100: T__29
                {
                mT__29(); 


                }
                break;
            case 17 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:106: T__30
                {
                mT__30(); 


                }
                break;
            case 18 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:112: T__31
                {
                mT__31(); 


                }
                break;
            case 19 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:118: VOLTAGE
                {
                mVOLTAGE(); 


                }
                break;
            case 20 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:126: WS
                {
                mWS(); 


                }
                break;
            case 21 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:129: FLOAT
                {
                mFLOAT(); 


                }
                break;
            case 22 :
                // D:\\stuff\\Dropbox\\eclipse\\EmbeddedDC\\war\\WEB-INF\\classes\\expressionEvaluator\\GateGrammar.g:1:135: MATH
                {
                mMATH(); 


                }
                break;

        }

    }


 

}