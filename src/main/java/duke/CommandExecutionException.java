package duke;

/**
 * Represents an exception that occurs during the execution of a command.
 */
public class CommandExecutionException extends DukeException {
    public CommandExecutionException(String message) {
        super(message);
    }
}
