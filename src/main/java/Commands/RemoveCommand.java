package Commands;

import TaskLists.TaskList;
import UiRelated.Ui;

/**
 * The removeCommand class represents a command to remove a task from the list.
 */
public class RemoveCommand extends Command {
    private final int index;

    /**
     * Constructs a removeCommand object with the specified index.
     *
     * @param index The index of the task to be removed.
     */
    public RemoveCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the removeCommand by removing the task from the TaskList and displaying a message in the Ui.
     *
     * @param taskList The TaskList object containing the tasks.
     * @param ui       The Ui object for displaying messaages.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        String task = taskList.removeTask(index);
        return ui.showMessages("HASSNT\n\n" + "Noted. I've removed this task: " + task);
    }
}
