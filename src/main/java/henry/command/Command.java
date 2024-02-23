package henry.command;

import henry.HenryException;
import henry.Storage;
import henry.TaskList;

/**
 * Represents a command object that can be executed
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The list of tasks.
     * @param storage The storage object.
     * @throws HenryException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws HenryException;

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
