package guanguan;

/**
 * Represents a GgException that will be thrown in the event of an error.
 */
public class GgException extends Exception {
    /**
     * Constructor for GgException.
     *
     * @param errorMessage Error message to be displayed.
     */
    public GgException(String errorMessage) {
        super(errorMessage);
    }
}
