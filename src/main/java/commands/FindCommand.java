package commands;

import java.util.List;

import storage.StorageManager;
import tasklist.TaskList;
import tasks.Task;

/**
 * represents a command to find tasks containing a specified keyword in the task list.
 */
public class FindCommand extends Command {
    protected String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param keyword The keyword to search for within the given task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand, searching for tasks containing the specified keyword
     * within the given list of tasks and prints the matching tasks.
     *
     * @param tasks The task list to search through
     * @param storageManager The storage manager (not used in this command).
     * @return String print output.
     */
    public String execute(TaskList tasks, StorageManager storageManager) {
        StringBuilder output = new StringBuilder();
        List<Task> tasksContainingKeyword = tasks.find(this.keyword);
        if (tasksContainingKeyword.size() == 0) {
            output.append("No matching tasks.\n");
        }
        output.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasksContainingKeyword.size(); i++) {
            Task t = tasksContainingKeyword.get(i);
            String str = String.format("%d. %s\n", i + 1, t);
            output.append(str);
        }
        return output.toString();
    }

    /**
     * Indicates whether the FindCommand is an exit command.
     *
     * @return False, as FindCommand is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
