package pico;

public class RuntimeError extends Error {
    public RuntimeError(String message)
    {
        super(message);
    }

    public RuntimeError(Exception cause) {
        super(cause);
    }
}