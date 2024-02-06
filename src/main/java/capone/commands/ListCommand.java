package capone.commands;

import capone.Storage;
import capone.TaskList;
import capone.ui.Ui;

/**
 * Represents a command to list all tasks in the TaskList.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand, displaying the list of tasks to the user.
     *
     * @param taskList The TaskList to be displayed.
     * @param ui       The Ui to interact with the user.
     * @param storage  The Storage for saving data (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sendList(taskList);
    }
}
