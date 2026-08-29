grammar Tea;

program : clause+ EOF;

queryInput : clause EOF;

clause : fact DOT;

fact : term;

term
	: ATOM LPAREN termArgs RPAREN
	| ATOM
	| VARIABLE
	;

termArgs : term (COMMA term)* ;

ATOM : [a-z][a-zA-Z0-9_]*;
VARIABLE : [A-Z_][a-zA-Z0-9_]*;

LPAREN : '(';
RPAREN : ')';
COMMA  : ',';
DOT    : '.';

WS : [ \t\r\n]+ -> skip;
COMMENT : '%' ~[\r\n]* -> skip;