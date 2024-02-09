package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * UnknownCommand class represents a command that is not recognized by the application.
 * It extends the Command class and provides methods to execute the command.
 */
public class UnknownCommand extends Command {
    /**
     * Executes the unknown command by throwing an exception indicating that the command is not recognized.
     *
     * @param taskList The list of tasks (not used in this command).
     * @param ui       The user interface (not used in this command).
     * @param storage  The storage handler (not used in this command).
     * @throws DukeException Always throws an exception indicating that the command is not recognized.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("OOPS!!! The command you provided is not recognized.");
    }
}
