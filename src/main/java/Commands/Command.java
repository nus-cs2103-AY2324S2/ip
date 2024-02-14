package Commands;

import TaskLists.TaskList;
import UiRelated.Ui;

/**
 * The Command class represents a command to be extended by other subclasses.
 */
public abstract class Command {
    /**
     * Executes the command with the given TaskList and Ui.
     *
     * @param taskList The TaskList object containing the tasks.
     * @param ui       The Ui object for displaying messages.
     */
    public abstract String execute(TaskList taskList, Ui ui);
}
