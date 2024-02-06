package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public abstract class Command {

    /**
     * Executes the command given the TaskList, Ui and Storage.
     * @param tasks The current TaskList
     * @param storage The Storage for the session
     * @throws DukeException if there is an error executing the command
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Returns a true if it is a Byecommand, false otherwise.
     * @return true if command is a Byecommand, else false
     */
    public boolean isExit() {
        return false;
    }
}
