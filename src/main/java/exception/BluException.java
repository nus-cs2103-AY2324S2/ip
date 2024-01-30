package exception;

public class BluException extends Exception {
    public BluException(String message) {
        super("BluException:\n" + message);
    }
}
