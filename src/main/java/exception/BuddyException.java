package exception;

/**
 * Represents Exceptions specific to buddy.Buddy.
 */
public class BuddyException extends Exception {
    /**
     * Creates Exception with specific error message.
     *
     * @param msg Error message specified
     */
    public BuddyException(String msg) {
        super(msg);
    }
}
