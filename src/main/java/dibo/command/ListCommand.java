package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;

/**
 * Class to represent the list command.
 */
public class ListCommand extends Command {
    /**
     * Run the list tasks command.
     *
     * @param taskList The TaskList object which contains all the tasks.
     * @param ui The Ui object which is responsible for printing the message.
     * @param storage The Storage object which is responsible to save the changes into a text file.
     */
    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList.getDisplayFormat());
    }
}
