package capone.commands;

import capone.TaskList;
import capone.Storage;
import capone.Ui;

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
        for (int i = 0; i < taskList.getSize(); i++) {
            ui.sendMessage(String.format("%d. %s\n", i + 1, taskList.getTask(i).toString()));
        }
    }
}
