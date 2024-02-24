package commands;

import java.io.IOException;

/**
 * Represents a command to mark a task as completed.
 * A <code>MarkCompletedCommand</code> object corresponds to a command with an index
 * e.g., <code>"mark 1"</code>
 */
public class MarkCompletedCommand extends Command{
    int index;
    public static final String COMMAND_WORD = "mark";
    public MarkCompletedCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a task as completed.
     * @return a string representing the result of executing the command
     * @throws IOException if an I/O error occurs
     */
    @Override
    public String execute() throws IOException {
        this.taskList.getTask(this.index).markCompleted();
        String outputString = "I've marked this task as completed:\n"
                + this.taskList.getTask(this.index).toString();
        return outputString.toString();
    }
}