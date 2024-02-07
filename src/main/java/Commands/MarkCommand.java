package Commands;

import TaskLists.TaskList;
import UiRelated.Ui;

/**
 * The MarkCommand class represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand object with the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand by marking the task as done in the TaskList and displaying a message in the Ui.
     *
     * @param taskList The TaskList object containing the tasks.
     * @param ui       The Ui object for displaying messages.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        String task = taskList.showMark(index);
        return ui.showMessages("marked:\n " + task);
    }
}
