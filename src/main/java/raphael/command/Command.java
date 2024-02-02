package raphael.command;

import raphael.task.TaskList;
import raphael.ui.Ui;
import raphael.storage.Storage;

public abstract class Command {
    public static enum TYPE {
        CHECK,
        UNCHECK,
        DELETE
    }

    /**
     * Executes the current command. This method is made abstract so that the command subclasses must override it
     * and provide their own implementations.
     *
     * @param tasks the task list
     * @param ui the user interface object
     * @param storage the file I/O object
     * @throws raphael.exception.RaphaelException exception exclusive to Raphael
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws raphael.exception.RaphaelException;

    /**
     * Returns a boolean value indicating if the current command is the exit command.
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
