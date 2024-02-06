package command;

import task.TaskList;
import dook.Ui;
import dook.Storage;

public class ListCommand extends Command {

    /**
     * Lists the current tasks
     *
     * @param tasks The bot TaskList.
     * @param ui The user interface.
     * @param storage The storage interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.println("Here are your tasks!");
        ui.println(tasks.toString());
    }
}
