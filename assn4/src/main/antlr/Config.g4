grammar Config;

top : head val EOF;

head : '[' NAME ']' WS;

val : NAME '=' NAME WS;

NAME : ('a'..'z'|'A'..'Z')+;

WS : ('\r'|'\n'|'\t'|'\r\n');

