// Generated from D:/GitLab/cs664s16/assn7/src/main/antlr\CalcLang.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalcLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, ID=31, INT=32, 
		FLOAT=33, STR=34, WS=35, COMMENT=36;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "T__27", "T__28", "T__29", "ID", "INT", "FLOAT", "STR", 
		"NL", "WS", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'var'", "'='", "'print'", "'while'", "'do'", "'if'", "'then'", 
		"'else'", "'begin'", "'end'", "'-'", "'!'", "'^'", "'*'", "'/'", "'%'", 
		"'+'", "'<'", "'<='", "'>'", "'>='", "'<>'", "'&'", "'|'", "'('", "')'", 
		"','", "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "ID", "INT", "FLOAT", "STR", 
		"WS", "COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public CalcLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CalcLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2&\u00ef\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36"+
		"\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \6 \u00ad\n \r \16 \u00ae"+
		"\3!\6!\u00b2\n!\r!\16!\u00b3\3\"\6\"\u00b7\n\"\r\"\16\"\u00b8\3\"\3\""+
		"\7\"\u00bd\n\"\f\"\16\"\u00c0\13\"\3\"\3\"\5\"\u00c4\n\"\3\"\6\"\u00c7"+
		"\n\"\r\"\16\"\u00c8\5\"\u00cb\n\"\3#\3#\7#\u00cf\n#\f#\16#\u00d2\13#\3"+
		"#\3#\3$\3$\3$\5$\u00d9\n$\3%\3%\6%\u00dd\n%\r%\16%\u00de\3%\3%\3&\3&\7"+
		"&\u00e5\n&\f&\16&\u00e8\13&\3&\3&\5&\u00ec\n&\3&\3&\4\u00d0\u00e6\2\'"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37"+
		"= ?!A\"C#E$G\2I%K&\3\2\6\5\2C\\aac|\3\2\62;\4\2\f\f\17\17\4\2\13\13\""+
		"\"\u00fa\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\3M\3\2\2\2\5O\3\2\2\2\7S\3\2\2\2\tU\3\2\2\2\13"+
		"[\3\2\2\2\ra\3\2\2\2\17d\3\2\2\2\21g\3\2\2\2\23l\3\2\2\2\25q\3\2\2\2\27"+
		"w\3\2\2\2\31{\3\2\2\2\33}\3\2\2\2\35\177\3\2\2\2\37\u0081\3\2\2\2!\u0083"+
		"\3\2\2\2#\u0085\3\2\2\2%\u0087\3\2\2\2\'\u0089\3\2\2\2)\u008b\3\2\2\2"+
		"+\u008e\3\2\2\2-\u0090\3\2\2\2/\u0093\3\2\2\2\61\u0096\3\2\2\2\63\u0098"+
		"\3\2\2\2\65\u009a\3\2\2\2\67\u009c\3\2\2\29\u009e\3\2\2\2;\u00a0\3\2\2"+
		"\2=\u00a5\3\2\2\2?\u00ac\3\2\2\2A\u00b1\3\2\2\2C\u00b6\3\2\2\2E\u00cc"+
		"\3\2\2\2G\u00d8\3\2\2\2I\u00dc\3\2\2\2K\u00e2\3\2\2\2MN\7=\2\2N\4\3\2"+
		"\2\2OP\7x\2\2PQ\7c\2\2QR\7t\2\2R\6\3\2\2\2ST\7?\2\2T\b\3\2\2\2UV\7r\2"+
		"\2VW\7t\2\2WX\7k\2\2XY\7p\2\2YZ\7v\2\2Z\n\3\2\2\2[\\\7y\2\2\\]\7j\2\2"+
		"]^\7k\2\2^_\7n\2\2_`\7g\2\2`\f\3\2\2\2ab\7f\2\2bc\7q\2\2c\16\3\2\2\2d"+
		"e\7k\2\2ef\7h\2\2f\20\3\2\2\2gh\7v\2\2hi\7j\2\2ij\7g\2\2jk\7p\2\2k\22"+
		"\3\2\2\2lm\7g\2\2mn\7n\2\2no\7u\2\2op\7g\2\2p\24\3\2\2\2qr\7d\2\2rs\7"+
		"g\2\2st\7i\2\2tu\7k\2\2uv\7p\2\2v\26\3\2\2\2wx\7g\2\2xy\7p\2\2yz\7f\2"+
		"\2z\30\3\2\2\2{|\7/\2\2|\32\3\2\2\2}~\7#\2\2~\34\3\2\2\2\177\u0080\7`"+
		"\2\2\u0080\36\3\2\2\2\u0081\u0082\7,\2\2\u0082 \3\2\2\2\u0083\u0084\7"+
		"\61\2\2\u0084\"\3\2\2\2\u0085\u0086\7\'\2\2\u0086$\3\2\2\2\u0087\u0088"+
		"\7-\2\2\u0088&\3\2\2\2\u0089\u008a\7>\2\2\u008a(\3\2\2\2\u008b\u008c\7"+
		">\2\2\u008c\u008d\7?\2\2\u008d*\3\2\2\2\u008e\u008f\7@\2\2\u008f,\3\2"+
		"\2\2\u0090\u0091\7@\2\2\u0091\u0092\7?\2\2\u0092.\3\2\2\2\u0093\u0094"+
		"\7>\2\2\u0094\u0095\7@\2\2\u0095\60\3\2\2\2\u0096\u0097\7(\2\2\u0097\62"+
		"\3\2\2\2\u0098\u0099\7~\2\2\u0099\64\3\2\2\2\u009a\u009b\7*\2\2\u009b"+
		"\66\3\2\2\2\u009c\u009d\7+\2\2\u009d8\3\2\2\2\u009e\u009f\7.\2\2\u009f"+
		":\3\2\2\2\u00a0\u00a1\7v\2\2\u00a1\u00a2\7t\2\2\u00a2\u00a3\7w\2\2\u00a3"+
		"\u00a4\7g\2\2\u00a4<\3\2\2\2\u00a5\u00a6\7h\2\2\u00a6\u00a7\7c\2\2\u00a7"+
		"\u00a8\7n\2\2\u00a8\u00a9\7u\2\2\u00a9\u00aa\7g\2\2\u00aa>\3\2\2\2\u00ab"+
		"\u00ad\t\2\2\2\u00ac\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00ac\3\2"+
		"\2\2\u00ae\u00af\3\2\2\2\u00af@\3\2\2\2\u00b0\u00b2\t\3\2\2\u00b1\u00b0"+
		"\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"B\3\2\2\2\u00b5\u00b7\t\3\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2"+
		"\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00be"+
		"\7\60\2\2\u00bb\u00bd\t\3\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2"+
		"\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00ca\3\2\2\2\u00c0\u00be"+
		"\3\2\2\2\u00c1\u00c3\7g\2\2\u00c2\u00c4\7/\2\2\u00c3\u00c2\3\2\2\2\u00c3"+
		"\u00c4\3\2\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00c7\t\3\2\2\u00c6\u00c5\3\2"+
		"\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9"+
		"\u00cb\3\2\2\2\u00ca\u00c1\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cbD\3\2\2\2"+
		"\u00cc\u00d0\7$\2\2\u00cd\u00cf\13\2\2\2\u00ce\u00cd\3\2\2\2\u00cf\u00d2"+
		"\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d3\3\2\2\2\u00d2"+
		"\u00d0\3\2\2\2\u00d3\u00d4\7$\2\2\u00d4F\3\2\2\2\u00d5\u00d9\t\4\2\2\u00d6"+
		"\u00d7\7\17\2\2\u00d7\u00d9\7\f\2\2\u00d8\u00d5\3\2\2\2\u00d8\u00d6\3"+
		"\2\2\2\u00d9H\3\2\2\2\u00da\u00dd\t\5\2\2\u00db\u00dd\5G$\2\u00dc\u00da"+
		"\3\2\2\2\u00dc\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00dc\3\2\2\2\u00de"+
		"\u00df\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1\b%\2\2\u00e1J\3\2\2\2\u00e2"+
		"\u00e6\7%\2\2\u00e3\u00e5\13\2\2\2\u00e4\u00e3\3\2\2\2\u00e5\u00e8\3\2"+
		"\2\2\u00e6\u00e7\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00eb\3\2\2\2\u00e8"+
		"\u00e6\3\2\2\2\u00e9\u00ec\5G$\2\u00ea\u00ec\7\2\2\3\u00eb\u00e9\3\2\2"+
		"\2\u00eb\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\b&\3\2\u00eeL\3"+
		"\2\2\2\20\2\u00ae\u00b3\u00b8\u00be\u00c3\u00c8\u00ca\u00d0\u00d8\u00dc"+
		"\u00de\u00e6\u00eb\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}