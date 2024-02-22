package command;
import sky.Ui;
import task.Task;
import task.TaskList;

/**
 * Represents a command to unmark a task as done in the task list.
 */
public class UnMarkCommand extends Command {
    private int index;

    /**
     * Constructor for UnMarkCommand.
     * @param index Index of the task to be unmarked as done.
     */
    public UnMarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks a task as done in the task list and shows the user the unmarked task.
     * @param tasks Task list to unmark the task as done in.
     * @param ui Ui to display the unmarked task to the user.
     * @return String to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        Task task = tasks.unMark(index);
        return ui.showUnmarkTask(task);
    }
}
