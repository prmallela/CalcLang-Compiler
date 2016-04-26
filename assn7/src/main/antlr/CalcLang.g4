grammar CalcLang;

top  : prog EOF;

prog : (stmt ';')+
     ;

stmt : 'var' ID '=' expr                            #VarStmt
     | ID '=' expr                                  #AssignStmt
     | 'print' expr                                 #PrintStmt
     | 'while' expr 'do' stmt                       #WhileStmt
     | 'if' expr 'then' stmt                        #IfStmt
     | 'if' expr 'then' stmt ';' 'else' stmt        #IfElseStmt
     | 'begin' prog 'end'                           #BlockStmt
     ;

expr : op=('-'|'!') expr                            #NegExpr
     | <assoc=right> left=expr op='^' right=expr    #OpExpr
     | left=expr op=('*'|'/'|'%') right=expr        #OpExpr
     | left=expr op=('+'|'-') right=expr            #OpExpr
     | left=expr op=('<'|'<='|'>'|'>=') right=expr  #OpExpr
     | left=expr op=('='|'<>') right=expr           #OpExpr
     | left=expr op='&' right=expr                  #OpExpr
     | left=expr op='|' right=expr                  #OpExpr
     | 'if' expr 'then' expr 'else' expr            #IfExpr
     | ID '(' ')'                                   #FunExpr
     | ID '(' expr (',' expr)* ')'                  #FunExpr
     | '(' expr ')'                                 #ParenExpr
     | INT                                          #IntExpr
     | FLOAT                                        #FloatExpr
     | STR                                          #StringExpr
     | ID                                           #VarExpr
     | bool                                         #BoolExpr
     ;

bool : 'true'
     | 'false'
     ;

ID   : [_a-zA-Z]+ ;
INT  : [0-9]+;
FLOAT: [0-9]+ '.' [0-9]* ('e' '-'? [0-9]+)?;
STR  : '"' .*? '"';

fragment NL : ('\n'|'\r'|'\r\n');

WS   : (' '|'\t'|NL)+ -> skip;
COMMENT: '#' .*? (NL|EOF) -> channel(HIDDEN);
