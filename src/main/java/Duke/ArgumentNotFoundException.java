package duke;

/**
 * Represents an exception thrown when a Duke application command is used without providing the necessary arguments.
 */
public class ArgumentNotFoundException extends DukeException {

    /**
     * Constructs a new ArgumentNotFoundException with a descriptive error message that includes the affected command.
     *
     * @param command The command that was missing required arguments.
     */
    public ArgumentNotFoundException(String command) {
        super("Arguments are required for " + command);
    }
}
