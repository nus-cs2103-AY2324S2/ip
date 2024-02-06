package zack.commands;

import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    /**
     * Executes the command to display a goodbye message and exit the application.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI for displaying messages to the user.
     * @param storage The Storage for saving and loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbyeMessage();
    }
}
