package thames.command;

import thames.TaskList;
import thames.Ui;
import thames.Storage;
import thames.ThamesException;

/**
 * Abstract command class that is executable.
 */
public abstract class Command {
    /**
     * Executes the command based on its type, e.g. AddCommand, EditCommand, ExitCommand etc.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ThamesException;

    /**
     * Checks whether this is an ExitCommand.
     * @return True if this is an ExitCommand.
     */
    public boolean isExit() {
        return (this instanceof ExitCommand);
    }
}
