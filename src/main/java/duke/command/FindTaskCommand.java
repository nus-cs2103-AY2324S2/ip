package duke.command;

import java.sql.SQLException;
import java.util.ArrayList;

import database.TaskOrm;

/**
 * Represents a command to find tasks from the task list.
 */
public class FindTaskCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private final String searchTerm;
    private final TaskOrm tm = new TaskOrm();

    public FindTaskCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public FindTaskCommand() {
        this.searchTerm = "";
    }

    @Override
    public String execute() {
        try {
            ArrayList<task.Task> tasks = this.tm.list(this.searchTerm);

            if (tasks.isEmpty()) {
                return "No tasks found!\n";
            }

            StringBuilder sb = new StringBuilder();
            for (task.Task task : tasks) {
                sb.append(String.format("%d. %s\n", task.getTaskID(), task));
            }
            return sb.toString();
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public String explain() {
        return "Finds tasks from the task list that match the search term.\n"
                + "Format: find <search term>\n"
                + "Example: find book";
    }
}
