package duke.command;

import java.sql.SQLException;

import database.TaskOrm;

/**
 * Represents a command to mark a task as not done from the task list.
 */
public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final int taskID;
    private final TaskOrm tm = new TaskOrm();

    public UnmarkTaskCommand(int taskID) {
        this.taskID = taskID;
    }

    public UnmarkTaskCommand() {
        this.taskID = -1;
    }

    @Override
    public String execute() {
        try {
            tm.unmark(taskID);
            task.Task task = this.tm.get(taskID);
            return "Ok, I've marked this task as not done yet:\n" + "  " + task + "\n";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public String explain() {
        return "Marks the task with the specified index as not done.\n"
                + "Format: unmark <index>\n"
                + "Example: unmark 3";
    }
}
