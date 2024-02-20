package yue.command;

import yue.Storage;
import yue.tasks.TaskList;
import yue.tasks.Task;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a command to find tasks containing a specified keyword.
 */

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        assert keyword != null && !keyword.isEmpty() : "Keyword cannot be null or empty";
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks containing the keyword.
     * Displays the matching tasks in the UI.
     *
     * @param tasks   The list of tasks to search within.
     * @param storage The storage for saving tasks.
     */


    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";

        List<Task> matchingTasks = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks.getAllTasks()) {
            if (task.getTask().contains(keyword)) {
                matchingTasks.add(task);
                sb.append(task.toString()).append(System.lineSeparator());
            }
        }

        if (sb.length() >= 1) {
            String matchingTasksString = "Here are the matching tasks in your list:\n"
                    + sb.toString() + "\n";
            return matchingTasksString;
        } else {
            String matchingTasksString = "There is no task matching the word: " + keyword;
            return matchingTasksString;

        }


    }


    /**
     * Indicates whether this command is an exit command.
     *
     * @return False, as FindCommand does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

