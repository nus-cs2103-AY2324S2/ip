package exceptions;

public class BluException extends Exception {
    public BluException(String message) {
        super("BluException:\n" + message);
    }
}
