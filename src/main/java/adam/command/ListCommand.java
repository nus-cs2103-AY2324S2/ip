package ada.command;

import ada.AdaException;
import ada.Storage;
import ada.task.Task;
import ada.task.TaskList;
import ada.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdaException {
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
