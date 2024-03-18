package commands;

/**
 * Represents a command that returns an error message when executed.
 * This class implements the {@link Command} interface.
 */
public class CommandError implements Command {

    /**
     * The error message associated with this command.
     */
    private final String message;

    /**
     * Constructs a new CommandError object with the specified error message.
     *
     * @param message the error message to be associated with this command.
     */
    public CommandError(String message) {
        this.message = message;
    }

    /**
     * Executes the command and returns the error message.
     *
     * @return the error message associated with this command.
     */
    @Override
    public String execute() {
        return this.message;
    }
}
