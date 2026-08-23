grammar Tea;

clause : fact DOT

fact : term

term
	: ATOM '(' termArgs ')'
	| ATOM
	;

termArgs : ATOM (COMMA ATOM)* ;

ATOM : [a-z][a-zA-Z0-9_]*;

LPAREN : '(';
RPAREN : ')';
COMMA  : ',';
DOT    : '.';

WS : [ \t\r\n]+ -> skip;