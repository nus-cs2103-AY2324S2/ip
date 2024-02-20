package exceptions;

/**
 *  Serves as a base class for all custom exceptions in Blawg.
 */
public class BlawgException extends Exception {
    public BlawgException() {
        super();
    }
    public BlawgException(String message) {
        super(message);
    }
}
