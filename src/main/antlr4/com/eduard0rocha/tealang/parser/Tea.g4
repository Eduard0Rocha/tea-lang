grammar Tea;

clause : fact DOT;

fact : term;

term
	: ATOM LPAREN termArgs RPAREN
	| ATOM
	;

termArgs : ATOM (COMMA ATOM)* ;

ATOM : [a-z][a-zA-Z0-9_]*;

LPAREN : '(';
RPAREN : ')';
COMMA  : ',';
DOT    : '.';

WS : [ \t\r\n]+ -> skip;