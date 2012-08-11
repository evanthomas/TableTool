tree grammar ExpressionEvaluator;

options {
  language     = Java;
  tokenVocab   = ExpressionGrammar;
  ASTLabelType = CommonTree;
}

@header {
package expressionEvaluator;
import java.lang.Math;
import expressionHandling.SymbolTable;
}

@members {

private double v;
private double t;
private SymbolTable symbolTable;

public void setV(double v) { this.v = v; }
public void setT(double t) { this.t = t; }
public void setSymbolTable(SymbolTable s) { this.symbolTable = s; }

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

private double SymbolTableLookup(String varName) {

return 0;
}
}

// An expression that evaulate as a double
gateFunction returns [double result]
: e=expression EOF { result=e; }
;

symbolName returns [String name]
: ID EOF { name=$ID.text; }
;

expression returns [double result]
: ^('+' op1=expression op2=expression) { result = op1+op2; }
| ^('-' op1=expression op2=expression) { result = op1-op2; }
| ^('*' op1=expression op2=expression) { result = op1*op2; }
| ^('/' op1=expression op2=expression) { result = op1/op2; }
| ^('^' op1=expression op2=expression) { result = Math.pow(op1,op2); }
| ^(NEGATION op=expression) { result = -op; }
| ^(ID op1=expression (op2=expression)?) { result = math($ID.text, op1, op2); }
| ^('if' b=logical op1=expression op2=expression) { result = b?op1:op2; }
| FLOAT { result = Double.parseDouble($FLOAT.text); }
| ID { result = SymbolTableLookup($ID.text); }
| VOLTAGE { result = v;}
| TIME { result = t;}
;

logical returns [boolean truth]
: ^('!' op=logical) { truth = !op; }
| ^('&&' op1=logical op2=logical) { truth = op1&&op2; }
| ^('||' op1=logical op2=logical) { truth = op1||op2; }
| (b=comparison) { truth = b; }
;

comparison returns [boolean truth]
: ^('>'  op1=expression op2=expression) { truth = op1>op2; }
| ^('<'  op1=expression op2=expression) { truth = op1<op2; }
| ^('>=' op1=expression op2=expression) { truth = op1>=op2; }
| ^('<=' op1=expression op2=expression) { truth = op1<=op2; }
| ^('==' op1=expression op2=expression) { truth = op1==op2; }
| ^('!=' op1=expression op2=expression) { truth = op1!=op2; }
;

