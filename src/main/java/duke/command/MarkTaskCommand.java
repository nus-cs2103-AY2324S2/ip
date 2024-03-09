package duke.command;

import java.sql.SQLException;

import database.TaskOrm;

/**
 * Represents a command to delete a task from the task list.
 */
public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private final int taskID;
    private final TaskOrm tm = new TaskOrm();

    public MarkTaskCommand(int taskID) {
        this.taskID = taskID;
    }

    public MarkTaskCommand() {
        this.taskID = -1;
    }

    @Override
    public String execute() {
        try {
            tm.mark(taskID);
            task.Task task = this.tm.get(taskID);
            return "Nice! I've marked this task as done:\n" + "  " + task + "\n";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public String explain() {
        return "Marks the task with the specified index as done.\n"
                + "Format: mark <index>\n"
                + "Example: mark 3";
    }
}
