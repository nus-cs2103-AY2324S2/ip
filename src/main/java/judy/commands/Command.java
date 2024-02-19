package judy.commands;

import judy.storage.Storage;
import judy.ui.Ui;

/**
 * This is an abstract class which have a generic subclasses
 * to execute specific command and provide information about whether
 * the command signals an exit from the application.
 */
public abstract class Command {

    /**
     * Executes the specific action associated with the command.
     *
     * @param storage The storage component responsible for managing data storage.
     * @param ui      The user interface component for interacting with the user.
     */
    public abstract void execute(Storage storage, Ui ui);

    /**
     * Indicates whether the execution of this command signals an exit from the application.
     *
     * @return True if the command represents an exit, false otherwise.
     */
    public abstract boolean isExit();

}
