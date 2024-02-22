package command;

import sky.Ui;
import task.Task;
import task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand.
     * @param index Index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a task from the task list and shows the user the deleted task.
     * @param tasks Task list to delete the task from.
     * @param ui Ui to display the deleted task to the user.
     * @return String to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        Task removedTask = tasks.delete(index);
        return ui.showDeleteTask(removedTask, tasks.size());
    }
}
