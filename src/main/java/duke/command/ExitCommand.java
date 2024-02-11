package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class that inherits from the Command class.
 * Represents a command to exit the Duke program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand by displaying a goodbye message to the user.
     *
     * @param tasks   The TaskList that holds the list of tasks.
     * @param ui      The Ui to interact with the user.
     * @param storage The Storage to save the tasks to a file.
     * @throws DukeException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showGoodbyeMsg();
    }

    /**
     * Checks if the ExitCommand is an exit command.
     *
     * @return true, as the ExitCommand is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
