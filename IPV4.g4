grammar IPV4;

ipv4 : Number Dot Number Dot Number Dot Number ;

Number : [0-9]|[0-9][0-9]|'0'[0-9][0-9]|'1'[0-9][0-9]|'2'[0-4][0-9]|'25'[0-5];
Dot : '.' ;
ERROR : [0-9]+ ;
