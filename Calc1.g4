grammar Calc1;

root	: expr EOF ;

expr  	: expr PLUS expr
	 	| expr DIV expr
	 	| expr PLUS expr
	 	| expr MINUS expr
		| expr MUL expr
		| LPAREN expr RPAREN
	 	| factor
        ;

factor  : INT
        | ID
        ;

LPAREN : '(' ;
RPAREN : ')' ;

INT : [0-9]+ ;
PLUS: '+';
MINUS: '-';
MUL: '*';
DIV: '/';
ID : [a-zA-Z]+ ;
WS  : [ \t\r\n]+ -> skip ;
