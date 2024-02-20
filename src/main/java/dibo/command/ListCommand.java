package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;

/**
 * The LIstCommand class represents a command to list all the tasks in TaskList.
 */
public class ListCommand extends Command {
    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) {
        ui.storeList(taskList.getDisplayFormat());
    }
}
