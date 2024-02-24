package commands;

import storage.StorageManager;
import tasklist.TaskList;
import tasks.Task;

/**
 * Represents a command display all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand, displaying all tasks in the task list.
     *
     * @param tasks          The task list to be displayed.
     * @param storageManager The storage manager (not used in this command).
     * @return Null, as there is no task to return.
     */
    public Task execute(TaskList tasks, StorageManager storageManager) {
        if (tasks.getSize() == 0) {
            System.out.println("You currently have no tasks.");
        }
        System.out.println("Here is your list of tasks:");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task t = tasks.getTask(i);
            String str = String.format("%d. %s", i + 1, t);
            System.out.println(str);
        }
        return null;
    }

    /**
     * Indicates whether the ListCommand is an exit command.
     *
     * @return False, as ListCommand is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
