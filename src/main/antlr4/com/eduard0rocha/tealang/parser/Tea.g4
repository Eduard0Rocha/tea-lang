grammar Tea;

program : clause+ EOF;

clause : (fact | rule) DOT;

fact : term;
rule : term NECK term;

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
NECK   : ':-';
DOT    : '.';

WS : [ \t\r\n]+ -> skip;
COMMENT : '%' ~[\r\n]* -> skip;