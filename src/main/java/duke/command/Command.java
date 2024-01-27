package duke.command;


import duke.common.TaskList;
import duke.exception.DukeException;

import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Represents a Command initiated by the user
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Execute the command
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the Command is an exit command
     * @return Whether the command is an exit command
     */
    public boolean isExit() {
        return this.isExit;
    }
}
