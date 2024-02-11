package Thames.command;

import Thames.command.ExitCommand;
import Thames.TaskList;
import Thames.Ui;
import Thames.Storage;
import Thames.ThamesException;

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
