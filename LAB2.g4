lexer grammar LAB2;

INT : 'int' ;
MAIN : 'main' ;
IF : 'if' ;

LPAREN : '(' ;
RPAREN : ')' ;
LBRACE : '{' ;
RBRACE : '}' ;

PLUS   : '+' ;
MINUS  : '-' ;
ASSIGN : '=' ;

ID : [a-zA-Z_][0-9a-zA-Z_]* ;

LANGLE  : '<' ;   // less‑than 小于号
RANGLE  : '>' ;   // greater‑than 大于号
SEMI    : ';' ;   // semicolon 分号

fragment ESCAPED_CHAR
    : '\\' ('"')
    ;

STRING
    : '"' (ESCAPED_CHAR|~["])* '"'
    ;

NUM : [0-9][0-9]* ;

WS : [ \t\r\n]+ -> skip ;