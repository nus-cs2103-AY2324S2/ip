package fluffy;

/**
 * Represents an exception specific to Fluffy.
 */
public class FluffyException extends Exception {

    /**
     * Constructor for FluffyException.
     * @param errorMessage The error message to be displayed.
     */
    public FluffyException(String errorMessage) {
        super(errorMessage);
    }
}
