package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * @inheritDoc
 * Represents a command to print the list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Returns a command to print the list of tasks.
     */
    public ListCommand() {
    }

    /**
     * Prints the list of tasks.
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        for (Task t: tasks.getTasks()) {
            ui.showResult(t.toString());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
