package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class SaveCommand extends Command {

    /**
     * Constructor for Save Command.
     * */
    public SaveCommand() {

    }

    /**
     * Saves the current list to the local data file.
     *
     * @param tasks Current TaskList.
     * @param storage Current Storage.
     * @param ui Current Ui.
     * */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.storeToLocal(tasks);
        return "Your List has been saved !";
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
