package duke.commands;

import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class for executable commands
 */
public abstract class Command {

    /**
     * Performs updates to TaskList, Ui print statements
     * and Storage updates based on the command type
     *
     * @param tasks TaskList of current Duke.
     * @param ui Ui of current Duke.
     * @param storage Storage of current Duke.
     * @throws DukeException  If error executing the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException;

    /**
     * returns True if ExitCommand type
     */
    public boolean isExit() {
        return false;
    }

}
