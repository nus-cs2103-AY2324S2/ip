package Commands;

import TaskLists.TaskList;
import UiRelated.Ui;

/**
 * The unMarkCommand class represents a command to unmark a task and make it not done.
 */
public class UnMarkCommand extends Command {
    private final int index;

    /**
     * Constructs an unMarkCommand object with the specified index.
     *
     * @param index The index of the task to be marked as not done.
     */
    public UnMarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unMarkCommand by marking the task as not done in the TaskList and displaying a message in the Ui.
     *
     * @param taskList The TaskList object containing the tasks.
     * @param ui       The Ui object for displaying messages.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        String task = taskList.showUnmark(index);
        return ui.showMessages("removed:\n " + task);
    }
}
