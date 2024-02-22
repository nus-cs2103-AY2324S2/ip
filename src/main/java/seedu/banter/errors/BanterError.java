package seedu.banter.errors;


/**
 * Represents an error handled by Banter.
 */
public class BanterError extends Exception {
    /**
     * Constructs a new BanterError object.
     * @param error Error message.
     */
    public BanterError(String error) {
        super(error);
    }
}
