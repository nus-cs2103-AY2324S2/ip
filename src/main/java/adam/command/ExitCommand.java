package adam.command;

import adam.AdamException;
import adam.Storage;
import adam.task.TaskList;
import adam.ui.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AdamException {
        storage.save(taskList);
        return ui.showBye();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
