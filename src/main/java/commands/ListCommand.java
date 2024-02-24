package commands;

import java.io.IOException;

/**
 * Represents a command to list all tasks in the task list.
 * A <code>ListCommand</code> object corresponds to a command with no input
 * e.g., <code>"list"</code>
 */
public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    /**
     * Executes the command to list all tasks in the task list.
     * @return a string representing the result of executing the command
     * @throws IOException if an I/O error occurs
     */
    @Override
    public String execute() throws IOException {
        StringBuilder output = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            output.append((i+1) + ". " + taskList.getTask(i).toString() + "\n");
        }
        return output.toString();
    }
}
