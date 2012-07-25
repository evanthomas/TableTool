tree grammar GateFunctionEvaluator;

options {
  language     = Java;
  tokenVocab   = GateGrammar;
  ASTLabelType = CommonTree;
}

@header {
package expressionEvaluator;
import java.lang.Math;
}

@members {

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
}

gateFunction returns [double result]
: e=expression EOF { result=e; }
;

expression returns [double result]
: ^('+' op1=expression op2=expression) { result = op1+op2; }
| ^('-' op1=expression op2=expression) { result = op1-op2; }
| ^('*' op1=expression op2=expression) { result = op1*op2; }
| ^('/' op1=expression op2=expression) { result = op1/op2; }
| ^('^' op1=expression op2=expression) { result = Math.pow(op1,op2); }
| ^(NEGATION op=expression) { result = -op; }
| ^(MATH op1=expression (op2=expression)?) { result = math($MATH.text, op1, op2); }
| ^('if' t=logical op1=expression op2=expression) { result = t?op1:op2; }
| FLOAT { result = Double.parseDouble($FLOAT.text); }
| VOLTAGE { result = v;}
;

logical returns [boolean truth]
: ^('!' op=logical) { truth = !op; }
| ^('&&' op1=logical op2=logical) { truth = op1&&op2; }
| ^('||' op1=logical op2=logical) { truth = op1||op2; }
| (t=comparison) { truth = t; }
;

comparison returns [boolean truth]
: ^('>'  op1=expression op2=expression) { truth = op1>op2; }
| ^('<'  op1=expression op2=expression) { truth = op1<op2; }
| ^('>=' op1=expression op2=expression) { truth = op1>=op2; }
| ^('<=' op1=expression op2=expression) { truth = op1<=op2; }
| ^('==' op1=expression op2=expression) { truth = op1==op2; }
| ^('!=' op1=expression op2=expression) { truth = op1!=op2; }
;

