package badgpt.exceptions;

/**
 * General class of exceptions that arise from commands.
 */
public class CommandException extends BadException {

    /**
     * Creates a new CommandException with the specified message.
     *
     * @param message The error message.
     */
    public CommandException(String message) {
        super(message);
    }
}
