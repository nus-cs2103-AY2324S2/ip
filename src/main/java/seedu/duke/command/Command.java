package seedu.duke.command;


import seedu.duke.common.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;


/**
 * Represents a Command initiated by the user
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Execute the command
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the Command is an exit command
     *
     * @return Whether the command is an exit command
     */
    public boolean isExit() {
        return this.isExit;
    }
}
