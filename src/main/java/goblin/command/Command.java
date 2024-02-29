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
     * @param storage handle stroage
     * @throws OrkException when something is wrong
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws OrkException;

    /**
     * show the app is working
     * @return true
     */
    public boolean isWorking() {
        return true;
    }
}
