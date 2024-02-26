package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * The abstract class for all Commands.
 */
public abstract class Command {
    /**
     * The Command type.
     */
    protected String commandType = "";

    /**
     * Execute - abstract method overridden by all Commands.s
     *
     * @param taskList the task list
     * @param ui       the ui
     * @param storage  the storage
     * @throws DukeException the duke exception
     */
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws DukeException;

    /**
     * Is exit boolean.
     *
     * @return the boolean
     * @throws DukeException the duke exception
     */
    public boolean isExit() throws DukeException {
        return false;
    }
}
