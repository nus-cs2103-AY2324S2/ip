package chaterpillar.commands;

import chaterpillar.storage.Storage;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * <code>Command</code> specifically to print all the tasks
 * present in the list of tasks currently.
 *
 * @author marclamp
 */
public class ListAllCommand extends Command {

    /**
     * Prints all the tasks in the list currently.
     *
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     * @return reply from the ChatBot.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return new ListCommand(tasks).execute(tasks, ui, storage);
    }
}
