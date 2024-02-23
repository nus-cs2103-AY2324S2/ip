package notduke.command;

import notduke.notdukeexception.NotDukeException;
import notduke.storage.Storage;
import notduke.tasklist.TaskList;

public abstract class Command {

    /**
     * Executes the command given the TaskList, Ui and Storage.
     * @param tasks The current TaskList
     * @param storage The Storage for the session
     * @throws NotDukeException if there is an error executing the command
     */
    public abstract String execute(TaskList tasks, Storage storage) throws NotDukeException;

    /**
     * Returns a true if it is a Byecommand, false otherwise.
     * @return true if command is a Byecommand, else false
     */
    public boolean isExit() {
        return false;
    }
}
