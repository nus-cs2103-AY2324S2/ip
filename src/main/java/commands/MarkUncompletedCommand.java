package commands;

import java.io.IOException;

/**
 * Represents a command to mark a task as uncompleted.
 * A <code>MarkUncompletedCommand</code> object corresponds to a command with an index
 * e.g., <code>"unmark 1"</code>
 */
public class MarkUncompletedCommand extends Command{
    int index;
    public static final String COMMAND_WORD = "unmark";
    public MarkUncompletedCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a task as uncompleted.
     * @return a string representing the result of executing the command
     * @throws IOException if an I/O error occurs
     */
    @Override
    public String execute() throws IOException {
        this.taskList.getTask(this.index).markUncompleted();
        String outputString = "I've marked this task as uncompleted:\n"
                + this.taskList.getTask(this.index).toString();
        return outputString;
    }
}