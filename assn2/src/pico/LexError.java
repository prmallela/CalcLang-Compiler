package pico;

public class LexError extends Error {
    public LexError(int line, int column, String message) {
        super(message + " @" + line + ":" + column);
    }
}
