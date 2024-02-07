package judy.commands;

import judy.storage.Storage;
import judy.ui.Ui;

/**
 * This is an abstract class which have a generic subclasses
 * to execute specific command
 */
public abstract class Command {
    /**
     * Execute command and perform necessary actions
     *
     * @param storage The storage object that manages the data persistence
     */
    public abstract void execute(Storage storage, Ui ui);
    public abstract boolean isExit();

}
