package commands;

import exceptions.CalException;
import storage.StorageManager;
import tasklist.TaskList;
import tasks.Task;

/**
 * represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    protected int taskNum;

    /**
     * Constructs a MarkCommand to mark a task as done.
     * Task number is used to identify the task to be marked.
     *
     * @param taskNum The task number of the task to be marked as done.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the MarkCommand, marking the specified task as done in the task list.
     *
     * @param tasks          The task list containing the task.
     * @param storageManager The storage manager to save the changes.
     * @return String print output.
     * @throws CalException if the task number is invalid or if an error occurs.
     */
    public String execute(TaskList tasks, StorageManager storageManager) throws CalException {
        Task t = tasks.mark(taskNum);
        storageManager.save(tasks);
        StringBuilder output = new StringBuilder();
        output.append("Nice! I've marked this task as done:\n");
        output.append(t).append("\n");
        return output.toString();
    }

    /**
     * Indicates whether the MarkCommand is an exit command.
     *
     * @return False, as MarkCommand is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
