package james.commands;

import james.storage.Storage;
import james.tasklist.TaskList;
import james.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param tasks   Task list to be listed.
     * @param ui      Ui to interact with the user.
     * @param storage Storage to save the task list to.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
