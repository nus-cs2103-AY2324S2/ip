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
     * @return The task that has been unmarked.
     * @throws CalException if the task number is invalid or if an error occurs.
     */
    public Task execute(TaskList tasks, StorageManager storageManager) throws CalException {
        Task t = tasks.unmark(taskNum);
        storageManager.save(tasks);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        return t;
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
