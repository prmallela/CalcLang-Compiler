package pico;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void testImmediateEOF() {
        assertTokens("", "EOF");
    }

    @Test
    public void testBraces() {
        assertTokens("{}", "LBRACE", "RBRACE", "EOF");
    }

    @Test
    public void testBracesWithSpace() {
        assertTokens("{ }", "LBRACE", "RBRACE", "EOF");
    }

    @Test
    public void testBracesWithOtherWhitespace() {
        assertTokens("  {\n  \r\n \t  }\n",
                "LBRACE", "RBRACE", "EOF");
    }

    @Test
    public void testSimpleInteger() {
        assertTokens("  158  ", "INT(158)", "EOF");
    }

    @Test
    public void testNegativeInteger() {
        assertTokens(" -8 ", "INT(-8)", "EOF");
    }

    @Test
    public void testBigInteger() {
        assertTokens("29843746598265982758725760139875",
                "INT(29843746598265982758725760139875)",
                "EOF");
    }

    @Test
    public void testBasicString() {
        assertTokens(" (Hello world!)",
                "STR(Hello world!)",
                "EOF");
    }

    @Test
    public void testStringNestedParens() {
        assertTokens(" (This is (a) string  ) ",
                "STR(This is \\(a\\) string  )",
                "EOF");
    }

    @Test
    public void testStringActualNewline() {
        assertTokens(" (Newline\nwithin\nstring)",
                "STR(Newline\\nwithin\\nstring)",
                "EOF");
    }

    @Test
    public void testStringQuotedParens() {
        assertTokens(" (Quoted \\)parens\\(here) ",
                "STR(Quoted \\)parens\\(here)",
                "EOF");
    }

    @Test
    public void testStringQuotedNewline() {
        assertTokens(" (Newline\\nchar)",
                "STR(Newline\\nchar)",
                "EOF");
    }

    @Test
    public void testStringQuotedBackslash() {
        assertTokens(" (actual\\\\backslash)",
                "STR(actual\\backslash)",
                "EOF");
    }

    @Test
    public void testBasicSymbol() {
        assertTokens("/square", "SYM(square)", "EOF");
    }

    @Test
    public void testSymbolWithNumerals() {
        assertTokens("  /a492x ", "SYM(a492x)", "EOF");
    }

    @Test
    public void testSymbolWithPunctuation() {
        assertTokens("  /a!c/$#h ", "SYM(a!c/$#h)", "EOF");
    }

    @Test
    public void testSymbolWithBraces() {
        assertTokens("  /acc{ }",
                "SYM(acc)", "LBRACE", "RBRACE", "EOF");
    }

    @Test
    public void testStringEndsSymbol() {
        assertTokens("/foo(bar)",
                "SYM(foo)",
                "STR(bar)",
                "EOF");
    }

    @Test
    public void testBasicOperators() {
        assertTokens(" pop dup exch ",
                "OP(pop)", "OP(dup)", "OP(exch)", "EOF");
    }

    @Test
    public void testNewlineSplitsOperators() {
        assertTokens("pop\ndup\nexch",
                "OP(pop)", "OP(dup)", "OP(exch)", "EOF");
    }

    @Test
    public void testBlockDefinition() {
        assertTokens(
                "/square {dup mul}def",
                "SYM(square)",
                "LBRACE",
                "OP(dup)",
                "OP(mul)",
                "RBRACE",
                "OP(def)",
                "EOF"
        );
    }

    @Test
    public void testCommentOnly() {
        assertTokens("  % line comment\n", "EOF");
    }

    @Test
    public void testCommentNoNewline() {
        assertTokens(" % line comment to eof", "EOF");
    }

    @Test
    public void testCommentSplitsBraces() {
        assertTokens(" {% ignore this}}}\n} % keep that",
                "LBRACE", "RBRACE", "EOF");
    }

    @Test
    public void testCommentEndsSymbol() {
        assertTokens(" /foo%bar\n", "SYM(foo)", "EOF");
    }

    @Test
    public void testNonCommentWithinString() {
        assertTokens(" (string with % is not a comment\nokay?)",
                "STR(string with % is not a comment\\nokay?)",
                "EOF");
    }

    @Test
    public void testCommentEndsOperator() {
        assertTokens("dup%then\nexch", "OP(dup)", "OP(exch)", "EOF");
    }

    @Test
    public void testInterleaveSpacesComments() {
        assertTokens("  3  % 5\n  4  %boo\n", "INT(3)", "INT(4)", "EOF");
    }

    @Test (expected=LexError.class)
    public void testLexErrorOnInvalidChar() {
        new Lexer("\uD83D\uDE01").nextToken();
    }

    @Test(expected=LexError.class)
    public void testLexErrorOnBadQuotedChar() {
        new Lexer("(str\\%)").nextToken();
    }

    @Test
    public void testBracePositions() {
        assertTokensWithPositions(
                "{  {\n  }  \n}",
                "LBRACE@1:1",
                "LBRACE@1:4",
                "RBRACE@2:3",
                "RBRACE@3:1",
                "EOF");
    }

    @Test
    public void testPositionOfIntOpStrSym() {
        assertTokensWithPositions(
                " 29 xyz\n /foo (ok)",
                "INT(29)@1:2",
                "OP(xyz)@1:5",
                "SYM(foo)@2:2",
                "STR(ok)@2:7",
                "EOF"
        );
    }

    /** Helper functions for above tests.
     */
    private void assertTokens(String code, String... tokens) {
        assertTokensHelper(false, code, tokens);
    }

    private void assertTokensWithPositions(String code, String... tokens) {
        assertTokensHelper(true, code, tokens);
    }

    private void assertTokensHelper(boolean withPositions, String code,
                                    String... tokens) {
        /* Assemble both expected and actual tokens as List<>
         * type, because it has better output when they mismatch.
         */
        ArrayList<String> expected = Lists.newArrayList(tokens);
        ArrayList<String> actual = new ArrayList<>();
        Lexer lex = new Lexer(code);
        Token t;
        do {
            // Clear position info so we don't have to match it for most tests
            t = lex.nextToken();
            if(!withPositions) t.clearPosition();
            actual.add(t.toString());
        } while(t.type != Token.Type.EOF);

        assertEquals(expected, actual);
    }
}
