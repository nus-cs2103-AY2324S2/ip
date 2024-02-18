package micromanager.commands;

import micromanager.exceptions.DukeException;
import micromanager.storage.Storage;
import micromanager.storage.TaskList;

/**
 * UnknownCommand class represents a command that is not recognized by the application.
 * It extends the Command class and provides methods to execute the command.
 */
public class UnknownCommand extends Command {
    /**
     * Executes the unknown command by throwing an exception indicating that the command is not recognized.
     *
     * @param taskList The list of tasks (not used in this command).
     * @param storage  The storage handler (not used in this command).
     * @throws DukeException Always throws an exception indicating that the command is not recognized.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        throw new DukeException("OOPS!!! The command you provided is not recognized.");
    }
}
