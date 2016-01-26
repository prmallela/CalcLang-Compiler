package edu.liu.cs664s16;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommentLexerTest  {

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
}
