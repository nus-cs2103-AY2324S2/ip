package ben.commands;

import ben.storage.Storage;
import ben.tasks.TaskList;
import ben.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Creates a ListCommand.
     */
    public ListCommand() {
    }

    /**
     * Executes the ListCommand by displaying the list message and the current task list.
     *
     * @param tasks   The task list to be listed.
     * @param ui      The user interface to display messages.
     * @param storage The storage (not used in this command).
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showListMessage() +
                ui.showTaskList(tasks);
    }
}
