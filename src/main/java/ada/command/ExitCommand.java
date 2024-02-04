package ada.command;

import ada.AdaException;
import ada.Storage;
import ada.task.TaskList;
import ada.ui.Ui;

/**
 * @inheritDoc
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Returns a command to exit the program.
     */
    public ExitCommand() {
    }

    /**
     * Exits the program.
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdaException {
        storage.save(tasks);
        ui.showBye();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
