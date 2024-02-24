package commands;

import exceptions.CalException;
import storage.StorageManager;
import tasklist.TaskList;
import tasks.Task;


/**
 * represents a command to unmark a "completed" task.
 */
public class UnmarkCommand extends Command {
    protected int taskNum;

    /**
     * Constructs a UnmarkCommand to unmark a "completed" task.
     * Task number is used to identify the task to be unmarked.
     *
     * @param taskNum The task number of the task to be unmarked.
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the UnmarkCommand, unmarking the specified "completed" task in the task list.
     *
     * @param tasks          The task list containing the task.
     * @param storageManager The storage manager to save the changes.
     * @return String print output.
     * @throws CalException if the task number is invalid or if an error occurs.
     */
    public String execute(TaskList tasks, StorageManager storageManager) throws CalException {
        Task t = tasks.unmark(taskNum);
        storageManager.save(tasks);
        StringBuilder output = new StringBuilder();
        output.append("OK, I've marked this task as not done yet:\n");
        output.append(t).append("\n");
        return output.toString();
    }

    /**
     * Indicates whether the UnmarkCommand is an exit command.
     *
     * @return False, as UnmarkCommand is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
