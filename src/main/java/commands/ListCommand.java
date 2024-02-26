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
     * @return String print output.
     */
    public String execute(TaskList tasks, StorageManager storageManager) {
        StringBuilder output = new StringBuilder();
        if (tasks.getSize() == 0) {
            output.append("You currently have no tasks.\n");
        }
        output.append("Here is your list of tasks:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task t = tasks.getTask(i);
            String str = String.format("%d. %s\n", i + 1, t);
            output.append(str);
        }
        return output.toString();
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
