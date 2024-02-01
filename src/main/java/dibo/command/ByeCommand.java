package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;

/**
 * Class to represent the bye command.
 */
public class ByeCommand extends Command {

    /**
     * Run the bye command.
     *
     * @param taskList The TaskList object which contains all the tasks.
     * @param ui The Ui object which is responsible for printing the goodbye message.
     * @param storage The Storage object which is responsible to save the changes into a text file.
     */
    public void run(TaskList taskList, Ui ui, Storage storage) {
        ui.sayBye();
    }

    /**
     * Flag to the main program that there should not be any command after this.
     * @return A boolean true value to signal that this is the end.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
