grammar Config;

top :ignore* sec+ EOF;

ignore : WS? NL | WS? COMMENT;

sec : head NL ignore* val+;

head : '[' NAME ']' ;

val : WS? NAME WS? '=' WS? value ignore*;

value : (NAME|WS|SCHAR|NUM|COMMENT|'=')*;

NAME : ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'-')+;
NUM : [0-9]+;
NL : ('\r'|'\n'|'\r\n');
WS : (' '|'\t')+;
SCHAR : ('!'|'@'|'.'|'_'|'-'|'#'|'/'|','|'?'|'$')+;
COMMENT :'#' (~('\n'))*;

