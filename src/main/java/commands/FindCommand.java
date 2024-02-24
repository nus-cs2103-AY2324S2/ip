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
     * @return Null, as there is no task to return.
     */
    public Task execute(TaskList tasks, StorageManager storageManager) {
        List<Task> tasksContainingKeyword = tasks.find(this.keyword);
        if (tasksContainingKeyword.size() == 0) {
            System.out.println("No matching tasks.");
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasksContainingKeyword.size(); i++) {
            Task t = tasksContainingKeyword.get(i);
            String str = String.format("%d. %s", i + 1, t);
            System.out.println(str);
        }
        return null;
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
