package duke.command;

import java.sql.SQLException;

import database.TaskOrm;

/**
 * Represents a command to list all tasks from the task list.
 */
public class ListTaskCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private final TaskOrm tm = new TaskOrm();

    @Override
    public String execute() {
        try {
            if (tm.count() == 0) {
                return "No tasks added yet!\n";
            }
            StringBuilder sb = new StringBuilder();
            for (task.Task task : this.tm.list()) {
                sb.append(String.format("%d. %s\n", task.getTaskID(), task));
            }
            return sb.toString();
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public String explain() {
        return "Lists all tasks from the task list.";
    }
}
