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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String toReturn = "Here are your tasks!\n";
        toReturn += tasks.toString();
        return toReturn;
    }
}
