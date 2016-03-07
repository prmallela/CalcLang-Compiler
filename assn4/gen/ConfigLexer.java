// Generated from D:/GitLab/cs664s16/assn4/src/main/antlr\Config.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ConfigLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, NAME=4, NUM=5, NL=6, WS=7, SCHAR=8, COMMENT=9;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "NAME", "NUM", "NL", "WS", "SCHAR", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'['", "']'", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "NAME", "NUM", "NL", "WS", "SCHAR", "COMMENT"
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


	public ConfigLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Config.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\13<\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\5\6\5\35\n\5\r\5\16\5\36\3\6\6\6\"\n\6\r\6\16\6#\3"+
		"\7\3\7\3\7\5\7)\n\7\3\b\6\b,\n\b\r\b\16\b-\3\t\3\t\3\n\3\n\7\n\64\n\n"+
		"\f\n\16\n\67\13\n\3\n\3\n\3\n\3\n\3\65\2\13\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\3\2\7\4\2C\\c|\3\2\62;\4\2\f\f\17\17\4\2\13\13\"\"\7\2#"+
		"#%%.\61ABaa@\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\3\25\3\2\2\2\5"+
		"\27\3\2\2\2\7\31\3\2\2\2\t\34\3\2\2\2\13!\3\2\2\2\r(\3\2\2\2\17+\3\2\2"+
		"\2\21/\3\2\2\2\23\61\3\2\2\2\25\26\7]\2\2\26\4\3\2\2\2\27\30\7_\2\2\30"+
		"\6\3\2\2\2\31\32\7?\2\2\32\b\3\2\2\2\33\35\t\2\2\2\34\33\3\2\2\2\35\36"+
		"\3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37\n\3\2\2\2 \"\t\3\2\2! \3\2\2\2"+
		"\"#\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\f\3\2\2\2%)\t\4\2\2&\'\7\17\2\2\')\7"+
		"\f\2\2(%\3\2\2\2(&\3\2\2\2)\16\3\2\2\2*,\t\5\2\2+*\3\2\2\2,-\3\2\2\2-"+
		"+\3\2\2\2-.\3\2\2\2.\20\3\2\2\2/\60\t\6\2\2\60\22\3\2\2\2\61\65\7%\2\2"+
		"\62\64\13\2\2\2\63\62\3\2\2\2\64\67\3\2\2\2\65\66\3\2\2\2\65\63\3\2\2"+
		"\2\668\3\2\2\2\67\65\3\2\2\289\7\f\2\29:\3\2\2\2:;\b\n\2\2;\24\3\2\2\2"+
		"\b\2\36#(-\65\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}