grammar CalcLang;

top  : prog EOF;

prog : (stmt ';')+
     ;

stmt : ID '=' expr                                  #AssignStmt
     | 'print' expr                                 #PrintStmt
     ;

expr : op=('-'|'!') expr                            #NegExpr
     | <assoc=right> left=expr op='^' right=expr    #OpExpr
     | left=expr op=('*'|'/') right=expr            #OpExpr
     | left=expr op=('+'|'-') right=expr            #OpExpr
     | left=expr op=('<'|'<='|'>'|'>=') right=expr  #OpExpr
     | left=expr op=('='|'<>') right=expr           #OpExpr
     | left=expr op='&' right=expr                  #OpExpr
     | left=expr op='|' right=expr                  #OpExpr
     | 'if' expr 'then' expr 'else' expr            #IfExpr
     | ID '(' expr (',' expr)* ')'                  #FunExpr
     | '(' expr ')'                                 #ParenExpr
     | INT                                          #IntExpr
     | FLOAT                                        #FloatExpr
     | ID                                           #VarExpr
     | bool                                         #BoolExpr
     ;

bool : 'true'
     | 'false'
     ;

ID   : [_a-zA-Z]+ ;
INT  : [0-9]+;
FLOAT: [0-9]+ '.' [0-9]* ('e' '-'? [0-9]+)?;
WS   : (' '|'\n'|'\t'|'\r')+ -> skip;

COMMENT: '#' .*? '\n' -> channel(HIDDEN);
