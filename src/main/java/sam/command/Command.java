package sam.command;

import sam.SamException;
import sam.Storage;
import sam.TaskList;

/**
 * Represents a command to be executed by the application.
 *
 * This is an abstract class and should be subclassed to define specific command behaviors.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * This method should be implemented by subclasses to execute the specific behavior of the command.
     * It typically involves interacting with the task list and storage components.
     *
     * @param tasks the TaskList containing the tasks
     * @param storage the Storage object for data storage and retrieval
     * @throws SamException if an error occurs during command execution
     */
    public abstract String execute(TaskList tasks, Storage storage) throws SamException;

    /**
     * Checks if the command indicates an exit signal.
     *
     * Returns false by default. Subclasses can override this method to indicate whether
     * executing the command should terminate the application.
     *
     * @return false, indicating that the command does not trigger an exit
     */
    public boolean isExit() {
        return false;
    }
}
