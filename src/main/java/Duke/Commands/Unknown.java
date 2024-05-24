package Duke.Commands;

import Duke.Activities.ActivityList;
import Duke.Exception.CommandException;

/**
 * Represents a command to handle unknown or unrecognized commands.
 * Extends the {@link Command} class.
 */
public class Unknown extends Command {

    /**
     * The error message indicating the unknown or unrecognized command.
     */
    private final String ERROR_MESSAGE;

    /**
     * Constructor to initialize the Unknown command with the specified error message.
     *
     * @param errorMessage The error message indicating the unknown or unrecognized command.
     */
    public Unknown(String errorMessage) {
        super(errorMessage);
        ERROR_MESSAGE = errorMessage;
    }

    /**
     * Executes the command (no specific action for unknown commands).
     *
     * @param list The activity list (not used in this case).
     * @throws CommandException If an error occurs during execution (not expected for this command).
     */
    @Override
    public void execute(ActivityList list) throws CommandException {
        // No specific action for unknown commands
    }

    /**
     * Returns a string representation of the command's execution output.
     *
     * @return The error message indicating the unknown or unrecognized command.
     */
    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }
}
