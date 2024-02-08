package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a new {@code ExitCommand}.
     *
     * Exits the program.
     */
    public ExitCommand() {
    }

    /**
     * {@inheritDoc}
     *
     * Displays a message to the user indicating that the program is exiting.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
