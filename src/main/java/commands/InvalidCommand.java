package commands;

/**
 * Represents a command to be executed.
 * A <code>Command</code> object corresponds to a command with a description and a deadline
 * e.g., <code>"deadline return book /by 2020-12-12 1800"</code>
 */
public class InvalidCommand extends Command{
    String errorMessage;

    /**
     * Constructs an InvalidCommand object with an error message.
     * @param errorMessage the error message to be displayed
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the command to be executed.
     * @return a string representing the result of executing the command
     */
    @Override
    public String execute() {
        return this.errorMessage;
    }
}

