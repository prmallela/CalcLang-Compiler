grammar Config;

top: heading var EOF
   ;

heading : '[' NAME ']'
        ;

var : NAME '=' NAME
    | NAME
    | '/'NAME
    | COMMENT
    | COMMENT2
    | NESTCOMMENT
    ;

NAME : (('a'..'z')|('A'..'Z')|('0'..'9'))+
     | NUM
     | Schar
     ;

Schar : '@'
      | '.'
      |'--'
      |'-'
      ;

NUM : [0-9]+ ('.' [0-9]+)? ;


WS  : (' '|'\n'|'\t')+ -> skip;

COMMENT: '#' .*? '\n' -> channel(HIDDEN);
COMMENT2: '/*' .*? '*/' -> channel(HIDDEN);
NESTCOMMENT : '{-' (NESTCOMMENT|.)*? '-}' -> channel(HIDDEN) ;
