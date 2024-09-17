package zack.commands;

import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to list all tasks and displays them using the UI.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI for displaying messages to the user.
     * @param storage The Storage for saving and loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks.getAllTasks(), "NA", "NA");
    }
}
