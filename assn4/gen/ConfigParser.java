// Generated from D:/GitLab/cs664s16/assn4/src/main/antlr\Config.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ConfigParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, NAME=4, NUM=5, NL=6, WS=7, SCHAR=8, COMMENT=9;
	public static final int
		RULE_top = 0, RULE_sec = 1, RULE_head = 2, RULE_val = 3, RULE_value = 4;
	public static final String[] ruleNames = {
		"top", "sec", "head", "val", "value"
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

	@Override
	public String getGrammarFileName() { return "Config.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ConfigParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TopContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ConfigParser.EOF, 0); }
		public List<TerminalNode> NL() { return getTokens(ConfigParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ConfigParser.NL, i);
		}
		public List<SecContext> sec() {
			return getRuleContexts(SecContext.class);
		}
		public SecContext sec(int i) {
			return getRuleContext(SecContext.class,i);
		}
		public TopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_top; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConfigListener ) ((ConfigListener)listener).enterTop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConfigListener ) ((ConfigListener)listener).exitTop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConfigVisitor ) return ((ConfigVisitor<? extends T>)visitor).visitTop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopContext top() throws RecognitionException {
		TopContext _localctx = new TopContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_top);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(10);
				match(NL);
				}
				}
				setState(15);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(17); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(16);
				sec();
				}
				}
				setState(19); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(21);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SecContext extends ParserRuleContext {
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(ConfigParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ConfigParser.NL, i);
		}
		public List<ValContext> val() {
			return getRuleContexts(ValContext.class);
		}
		public ValContext val(int i) {
			return getRuleContext(ValContext.class,i);
		}
		public SecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConfigListener ) ((ConfigListener)listener).enterSec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConfigListener ) ((ConfigListener)listener).exitSec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConfigVisitor ) return ((ConfigVisitor<? extends T>)visitor).visitSec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SecContext sec() throws RecognitionException {
		SecContext _localctx = new SecContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			head();
			setState(25); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(24);
				match(NL);
				}
				}
				setState(27); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NL );
			setState(30); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(29);
				val();
				}
				}
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME || _la==WS );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeadContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(ConfigParser.NAME, 0); }
		public HeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConfigListener ) ((ConfigListener)listener).enterHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConfigListener ) ((ConfigListener)listener).exitHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConfigVisitor ) return ((ConfigVisitor<? extends T>)visitor).visitHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadContext head() throws RecognitionException {
		HeadContext _localctx = new HeadContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_head);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			match(NAME);
			setState(36);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(ConfigParser.NAME, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(ConfigParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ConfigParser.WS, i);
		}
		public List<TerminalNode> NL() { return getTokens(ConfigParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ConfigParser.NL, i);
		}
		public ValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_val; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConfigListener ) ((ConfigListener)listener).enterVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConfigListener ) ((ConfigListener)listener).exitVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConfigVisitor ) return ((ConfigVisitor<? extends T>)visitor).visitVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValContext val() throws RecognitionException {
		ValContext _localctx = new ValContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_val);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(38);
				match(WS);
				}
			}

			setState(41);
			match(NAME);
			setState(43);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(42);
				match(WS);
				}
			}

			setState(45);
			match(T__2);
			setState(47);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(46);
				match(WS);
				}
				break;
			}
			setState(49);
			value();
			setState(51); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(50);
				match(NL);
				}
				}
				setState(53); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NL );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(ConfigParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(ConfigParser.NAME, i);
		}
		public List<TerminalNode> WS() { return getTokens(ConfigParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ConfigParser.WS, i);
		}
		public List<TerminalNode> SCHAR() { return getTokens(ConfigParser.SCHAR); }
		public TerminalNode SCHAR(int i) {
			return getToken(ConfigParser.SCHAR, i);
		}
		public List<TerminalNode> NUM() { return getTokens(ConfigParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(ConfigParser.NUM, i);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ConfigListener ) ((ConfigListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ConfigListener ) ((ConfigListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ConfigVisitor ) return ((ConfigVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NAME) | (1L << NUM) | (1L << WS) | (1L << SCHAR))) != 0)) {
				{
				{
				setState(55);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NAME) | (1L << NUM) | (1L << WS) | (1L << SCHAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\13@\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\7\2\16\n\2\f\2\16\2\21\13\2\3\2\6\2\24"+
		"\n\2\r\2\16\2\25\3\2\3\2\3\3\3\3\6\3\34\n\3\r\3\16\3\35\3\3\6\3!\n\3\r"+
		"\3\16\3\"\3\4\3\4\3\4\3\4\3\5\5\5*\n\5\3\5\3\5\5\5.\n\5\3\5\3\5\5\5\62"+
		"\n\5\3\5\3\5\6\5\66\n\5\r\5\16\5\67\3\6\7\6;\n\6\f\6\16\6>\13\6\3\6\2"+
		"\2\7\2\4\6\b\n\2\3\4\2\6\7\t\nC\2\17\3\2\2\2\4\31\3\2\2\2\6$\3\2\2\2\b"+
		")\3\2\2\2\n<\3\2\2\2\f\16\7\b\2\2\r\f\3\2\2\2\16\21\3\2\2\2\17\r\3\2\2"+
		"\2\17\20\3\2\2\2\20\23\3\2\2\2\21\17\3\2\2\2\22\24\5\4\3\2\23\22\3\2\2"+
		"\2\24\25\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\27\3\2\2\2\27\30\7\2\2"+
		"\3\30\3\3\2\2\2\31\33\5\6\4\2\32\34\7\b\2\2\33\32\3\2\2\2\34\35\3\2\2"+
		"\2\35\33\3\2\2\2\35\36\3\2\2\2\36 \3\2\2\2\37!\5\b\5\2 \37\3\2\2\2!\""+
		"\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#\5\3\2\2\2$%\7\3\2\2%&\7\6\2\2&\'\7\4\2"+
		"\2\'\7\3\2\2\2(*\7\t\2\2)(\3\2\2\2)*\3\2\2\2*+\3\2\2\2+-\7\6\2\2,.\7\t"+
		"\2\2-,\3\2\2\2-.\3\2\2\2./\3\2\2\2/\61\7\5\2\2\60\62\7\t\2\2\61\60\3\2"+
		"\2\2\61\62\3\2\2\2\62\63\3\2\2\2\63\65\5\n\6\2\64\66\7\b\2\2\65\64\3\2"+
		"\2\2\66\67\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\t\3\2\2\29;\t\2\2\2:9\3"+
		"\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\13\3\2\2\2><\3\2\2\2\13\17\25\35"+
		"\")-\61\67<";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}