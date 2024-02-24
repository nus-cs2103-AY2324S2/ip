package commands;

import java.io.IOException;

/**
 * Represents a command to clear the task list.
 * A <code>ClearCommand</code> object corresponds to a command with no input
 * e.g., <code>"clear"</code>
 */
public class ClearCommand extends Command{
    public static final String COMMAND_WORD = "clear";

    /**
     * Executes the command to clear the task list.
     * @return a string representing the result of executing the command
     * @throws IOException if an I/O error occurs
     */
    @Override
    public String execute() throws IOException {
        this.taskList.clear();
        return "Cleared your cache!";
    }
}
