package tiny.exceptions;

/**
 * Represents the custom exception for this program.
*/
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
