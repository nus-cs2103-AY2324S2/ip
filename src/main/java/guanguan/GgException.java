package guanguan;

/**
 * Represents a GGException that will be thrown in the event of an error.
 */
public class GGException extends Exception {
    /**
     * Constructor for GGException.
     *
     * @param errorMessage Error message to be displayed.
     */
    public GGException(String errorMessage) {
        super(errorMessage);
    }
}