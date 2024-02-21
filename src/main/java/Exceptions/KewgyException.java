package exceptions;

/**
 * Custom Exception class, no practical uses as of yet
 */
public class KewgyException extends Exception {
    public KewgyException() {
    }

    public KewgyException(String message) {
        super(message);
    }
}
