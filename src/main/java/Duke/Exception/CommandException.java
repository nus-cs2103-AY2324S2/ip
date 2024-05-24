package Duke.Exception;

/**
 * Custom exception class for handling command-related exceptions in the Duke application.
 */
public class CommandException extends Throwable {

    /**
     * Constructs a new CommandException with the specified error message.
     *
     * @param msg The error message describing the command-related exception.
     */
    public CommandException(String msg) {
        super(msg);
    }
}
