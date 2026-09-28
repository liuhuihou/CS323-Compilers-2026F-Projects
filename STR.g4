grammar STR;

// ========= Fragment 辅助片段 =========
fragment ESCAPED_CHAR
    : '\\' ( '"' )
    ;


// ========= Lexer Token 词法规则 =========
STRING
    : '"' ( ESCAPED_CHAR | ~["] )* '"'
    ;

ID
    : [a-zA-Z_][a-zA-Z0-9_]*
    ;

WS
    : [ \t\r\n]+ -> skip   // 空白直接丢弃，不生成token
    ;

// ========= Parser 语法产生式（必须要有，适配‑visitor生成parser） =========
program
    : item* EOF
    ;

item
    : STRING
    | ID
    ;
