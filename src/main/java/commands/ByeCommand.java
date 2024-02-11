package commands;

/**
 * Represents a command to exit the program.
 * A <code>ByeCommand</code> object corresponds to a command with no input
 * e.g., <code>"bye"</code>
 */
public class ByeCommand extends Command{
    public static final String COMMAND_WORD = "bye";

    /**
     * Executes the command to exit the program.
     * @return a string representing the result of executing the command
     */
    @Override
    public String execute() {
        return "Bye! Hope to see you again.";
    }

}
