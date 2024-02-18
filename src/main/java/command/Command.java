package command;
import duke.DukeException;
import duke.TaskList;
import duke.UI;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @throws DukeException if there is an error during command execution.
     */
    public abstract String execute(TaskList tasks, UI ui) throws DukeException;

    public boolean isExit() {
        return false;
    }
}