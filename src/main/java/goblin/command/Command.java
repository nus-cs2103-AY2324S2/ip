package goblin.command;

import goblin.Storage;
import goblin.TaskList;
import goblin.Ui;
import goblin.exception.OrkException;

public abstract class Command {

    /**
     * crate a new Command object
     * @param tasks lis to f tasks
     * @param ui handle ui
     * @param storage handle storage
     * @throws OrkException when something is wrong
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws OrkException;

    /**
     * show the app is working
     * @return true
     */
    public boolean isWorking() {
        return true;
    }
}
