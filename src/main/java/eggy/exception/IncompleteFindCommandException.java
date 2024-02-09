package eggy.exception;

/**
 * Represents an exception when the user does not specify the keyword for finding tasks.
 */
public class IncompleteFindCommandException extends EggyException {
    /**
     * Constructs the IncompleteFindCommandException.
     */
    public IncompleteFindCommandException() {
        super(" Please specify the keyword for finding tasks.");
    }
}
