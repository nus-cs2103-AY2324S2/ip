package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * {@inheritDoc}
 *
 * This subclass executes when the user types in exit command.
 */
public class ExitCommand extends Command {
    /**
     * Constructs the class ExitCommand.
     */
    public ExitCommand() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.store(taskList);
        ui.showExitMessage();
    }

    /**
     * {@inheritDoc}
     */
    public  boolean isExit() {
        return true;
    }
}
