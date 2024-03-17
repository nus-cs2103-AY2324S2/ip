package hal.command;

import hal.task.TaskList;

/**
 * The DeleteCommand class represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a new DeleteCommand object.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command.
     *
     * @param taskList The TaskList containing the tasks.
     * @return A message indicating the result of the delete operation.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.removeTask(taskIndex);
    }
}
