package command;

import exception.TobiasException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    /**
     * Prints all the tasks in the current TaskList in the console.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @param ui Current Ui.
     * */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = "";
        try {
            result = tasks.printList();
        } catch (TobiasException e) {
            e.printMessage();
        }

        return result;
    }

    /**
     * Informs if this command is an Exit command.
     *
     * @return Boolean value of true if this command is an exit command.
     * */
    @Override
    public boolean isExit() {
        return false;
    }
}
