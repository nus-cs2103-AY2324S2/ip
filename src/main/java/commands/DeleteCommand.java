package commands;

import storage.StorageManager;
import tasklist.TaskList;
import tasks.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    protected int taskNum;

    /**
     * Constructs an DeleteCommand to delete a task
     * Task number is used to identify task to be deleted.
     *
     * @param taskNum The task number of the task to be marked as done.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the DeleteCommand, deleting the task to the task list and save the changes.
     *
     * @param tasks          The task list where the task will be deleted.
     * @param storageManager The storage manager to save the changes.
     * @return The deleted task.
     */
    public Task execute(TaskList tasks, StorageManager storageManager) {
        Task t = tasks.delete(taskNum);
        storageManager.save(tasks);
        System.out.println("Noted. I've removed this task");
        System.out.println(t);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getSize()));
        return t;
    }

    /**
     * Indicates whether the DeleteCommand is an exit command.
     *
     * @return False, as DeleteCommand is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
