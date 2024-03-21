package shon.exception;

/**
 * Represent the exception when an invalid command is input. This can be due to an empty user input, or an
 * action/command not from the allowed set of actions.
 */
public class CommandException extends Exception {
    /**
     * Creates the exception with the given message.
     *
     * @param message The message in the exception.
     */
    public CommandException(String message) {
        super(message);
    }
}
