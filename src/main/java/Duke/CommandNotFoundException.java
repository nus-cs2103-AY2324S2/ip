package duke;

/**
 * Represents an exception thrown when the user attempts to execute an invalid or unknown command in the Duke application.
 */
public class CommandNotFoundException extends DukeException{

    /**
     * Constructs a new CommandNotFoundException with a detailed error message, indicating the unrecognized command.
     *
     * @param command The invalid command that was entered by the user.
     */
    public CommandNotFoundException(String command) {
        super(command + " is not a valid command!");
    }
}
