package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class that inherits from Command class.
 * Represents a command to display help information to the user.
 */
public class HelpCommand extends Command {

    /**
     * Executes the HelpCommand by displaying the list of valid commands and the format
     * to the user.
     *
     * @param tasks   The TaskList that holds the list of tasks.
     * @param ui      The Ui to interact with the user.
     * @param storage The Storage to save the tasks to a file.
     * @throws DukeException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showHelpMsg();
    }

    /**
     * Checks if the HelpCommand is an exit command.
     *
     * @return false, as the HelpCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
