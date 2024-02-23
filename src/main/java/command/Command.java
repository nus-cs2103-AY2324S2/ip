package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Executes certain requests given by the user. Prints results on the screen, interacts with file management and
 * tasklist control
 */
public abstract class Command {

    /**
     * Executes the task prompted by the user, prints certain results on the screen, and makes modification
     * to the storage files and internal task list to have better control of the storage of tasks
     *
     * @param storage  Involved in file management
     * @param taskList Active during the execution of the program
     * @return A string that answers the user
     * @throws DukeException
     */
    public abstract String execute(Storage storage, TaskList taskList) throws DukeException;
}
