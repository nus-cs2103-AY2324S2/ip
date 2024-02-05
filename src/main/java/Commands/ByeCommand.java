package Commands;

import java.io.IOException;

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

    /**
     * Returns true if the command is an exit command.
     * @param command the command to be checked
     * @return true if the command is an exit command
     */
    public static boolean isExit(Command command) {
        return command instanceof ByeCommand; // instanceof returns false if it is null
    }
}
