package command;

import sky.Ui;
import task.Task;
import task.TaskList;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for MarkCommand.
     * @param index Index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as done in the task list and shows the user the marked task.
     * @param tasks Task list to mark the task as done in.
     * @param ui Ui to display the marked task to the user.
     * @return String to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        Task task = tasks.mark(index);
        return ui.showMarkTask(task);
    }
}
