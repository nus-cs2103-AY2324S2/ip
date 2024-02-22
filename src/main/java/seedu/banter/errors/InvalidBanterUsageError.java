package seedu.banter.errors;


/**
 * Represents an error that occurs when the user uses an invalid command.
 */
public class InvalidBanterUsageError extends BanterError {
    /**
     * Constructs a new InvalidBanterUsageError object.
     * @param error Error message.
     * @param usage Usage message.
     */
    public InvalidBanterUsageError(String error, String usage) {
        super(error + "\nUsage: " + usage);
    }
}
