package commands;

import tasks.Task;
import utils.Response;
import utils.TaskList;

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int i;

    /**
     * Constructs a DeleteCommand object with the index of the task to be deleted.
     *
     * @param i The index of the task to be deleted.
     */
    public DeleteCommand(int i) {
        this.i = i;
    }

    /**
     * Executes the delete command.
     *
     * @param taskList The task list object.
     */
    @Override
    public void execute(TaskList taskList) {
        Task deletedTask = taskList.deleteTask(i - 1); // Adjusting index to match 0-based indexing
        setResponse(Response.getDeleteTaskResponse(deletedTask, taskList.getLength()));
    }
}
