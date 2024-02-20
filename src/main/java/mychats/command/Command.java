package duke.command;
import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a command that can be executed.
 */
public class Command {

    /**
     * Executes the command given the TaskList, Ui, and Storage.
     *
     * @param tasks TaskList that contains the task list.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage used to load and save tasks.
     * @throws DukeException If there is an error executing the command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }

    /**
     * Checks if command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
