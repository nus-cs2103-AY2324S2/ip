package tiny.exceptions;

public class TinyException extends Exception {

    /**
     * Initializes TinyException.
     *
     * @param message Error message to be displayed.
     */
    public TinyException(String message) {
        super(message);
    }
}
