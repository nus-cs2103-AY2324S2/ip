package james.commands;

import james.storage.Storage;
import james.tasklist.TaskList;
import james.ui.Ui;

/**
 * Represents a command to terminate the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command which results in terminating the application.
     * This method should handle the necessary cleanup before exiting.
     *
     * @param tasks   The TaskList being managed.
     * @param ui      The Ui responsible for interactions with the user.
     * @param storage The Storage where the task list is persisted.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }


    /**
     * Indicates whether the application should terminate after the execution of
     * this command. For ExitCommand, it always returns true.
     *
     * @return true as the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
