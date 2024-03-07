package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * The abstract class for all Commands.
 */
public abstract class Command {
    protected String commandType = "";

    /**
     * Execute - abstract method overridden by all Commands.s
     *
     * @param taskList the task list
     * @param storage  the storage
     * @throws DukeException the duke exception
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;

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
