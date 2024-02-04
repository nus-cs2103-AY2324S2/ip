package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
