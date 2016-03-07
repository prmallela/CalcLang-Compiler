grammar Config;

top : NL* sec+ EOF;

sec : head NL+ val+;

head : '[' NAME ']' ;

val : WS? NAME WS? '=' WS? value NL+;

value : (NAME|WS|SCHAR|NUM)*;

NAME : ('a'..'z'|'A'..'Z')+;
NUM : [0-9]+;

NL : ('\r'|'\n'|'\r\n');
WS : (' '|'\t')+;
SCHAR : '!'|'@'|'.'|'_'|'-'|'#'|'/'|','|'?';
COMMENT : '#' .*? '\n' ->skip;

