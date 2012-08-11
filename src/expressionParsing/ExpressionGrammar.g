grammar ExpressionGrammar;

options {
  language = Java;
  output = AST;
  ASTLabelType = CommonTree;
  backtrack=true;
}

tokens {
NEGATION;
MATH;
}

@header {
package expressionEvaluator;

import java.util.LinkedList;
}

@lexer::header {
package expressionEvaluator;

import java.util.LinkedList;
}

@members {

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

}
gateExpression : add EOF! ;

symbol: ID EOF;

term 
  : FLOAT
  | VOLTAGE
  | TIME
  | ID
  | maths
  | '('! add ')'!
  | ternary
  ;

maths
  : ID^ '('! add ')'!          {checkSingleMath($ID.text);}
  | ID^ '('! add ','! add ')'! {checkDoubleMath($ID.text);}
  ;
  
unary
  :
  ('+'! | negation^)? term
  ;

negation
: 
'-' -> NEGATION
;

power
:
unary ('^'^ unary)*
;

mult
  :
  power  (('*'^ | '/'^) power)*
;

add
  :
  mult (('+'^ | '-'^) mult)*
  ;

ternary
   :
   'if'^ '('! or ','! add ','! add ')'!
   ;
   
logical
  : comparison 
  | '('! or ')'! 
  ;
  
 not
   : ('!'^)? comparison
 ;

and
  : logical ('&&'^ logical)*
;

or
  : and ('||'^ and)*
;

comparison
  : add ('>'^ | '<'^ | '>='^ | '<='^ | '=='^  | '!='^) add
  ;

  
  // Tokens
 VOLTAGE
  :
  (
    'v'
    | 'V'
  )
  ;
  
 TIME
  :
  (
    't'
    | 'T'
  )
  ;

WS
  :
  (
    ' '
    | '\n'
    | '\r'
    | '\f'
    | '\t'
  )+
  
   {
    $channel = HIDDEN;
   }
  ;

fragment
DIGIT
  :
  ('0'..'9')
  ;

fragment
DOT
  :
  '.'
  ;

fragment
EXPONENT
  :
  (
    'e'
    | 'E'
  )
  ;

fragment
SIGN
  :
  (
    '+'
    | '-'
  )
  ;

FLOAT
  :
  DIGIT* (DOT DIGIT*)? (EXPONENT SIGN? DIGIT+)?
  ;

fragment LETTER : 'a'..'z' | 'A'..'Z';
  ID : LETTER (LETTER|DIGIT)*;