package edu.liu.cs664s16;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CommentLexerTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    private void assertUnchanged(String s) {
        assertEquals(s, CommentLexer.stripComments(s));
    }

    private void assertChanged(String original, String expected) {
        assertEquals(expected, CommentLexer.stripComments(original));
    }

    @Test
    public void testNoComments() {
        assertUnchanged("Hello world");
    }

    @Test
    public void testParens() {
        assertUnchanged("This (has balanced) parens");
        assertUnchanged("This has (only (left");
        assertUnchanged("This has) only) right");
        assertUnchanged("This (is (nested) too).");
    }

    @Test
    public void testStars() {
        assertUnchanged("This has * one star");
        assertUnchanged("This has **** many stars");
    }

    @Test
    public void testAlmostBegin() {
        assertUnchanged("This has paren (and later star*");
        assertUnchanged("This has paren space star ( * okay?");
    }

    @Test
    public void testAlmostReopen() {
        assertChanged("This (*is comment*) :(", "This  :(");
    }

    @Test
    public void testEndNoBegin() {
        assertUnchanged("This is *) not a comment.");
    }

    @Test
    public void testEasyComment() {
        assertChanged(
                "Hello (*cruel*)world",
                "Hello world"
        );
    }

    @Test
    public void testSharedStar() {
        assertChanged(
                "Start (*) still comment til *) here",
                "Start  here"
        );
    }

    @Test
    public void testCommentWithStar() {
        assertChanged(
                "Hello (* x*y *)world",
                "Hello world"
        );
    }

    @Test
    public void testCommentWith2ndOpen() {
        assertChanged(
                "Hello (* comment (* same comment *) regular *)",
                "Hello  regular *)"
        );
    }

    @Test
    public void testCommentDoubleParen() {
        assertChanged(
                "Hello ((*comment*))okay",
                "Hello ()okay"
        );
    }

    @Test
    public void testCommentDoubleStar() {
        assertChanged(
                "Hello (*comment**)okay",
                "Hello okay"
        );
    }

    @Test
    public void testCommentWith2ndClose() {
        assertChanged(
                "Hello (* comment *) text *) again",
                "Hello  text *) again"
        );
    }

    @Test(expected = UnterminatedCommentExn.class)
    public void testUnterminatedComment() {
        CommentLexer.stripComments("Foo (* unending");
    }

    @Test(expected = UnterminatedCommentExn.class)
    public void testAlmostClose() {
        CommentLexer.stripComments("Foo (* unending*");
    }

    @Test
    public void testReallyLongString() {
        Random rng = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < 4096; i++) {
            buf.append('a' + rng.nextInt(26)); // Random letters
        }
        String expected = buf.toString();
        String code = "(* random! *)" + expected;
        assertChanged(code, expected);
    }

    @Test
    public void testMultipleComments() {
        assertChanged("Be(*1*)By(*2*)Bo", "BeByBo");
    }

}
