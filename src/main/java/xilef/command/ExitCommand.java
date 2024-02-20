package xilef.command;

import xilef.Storage;
import xilef.Ui;
import xilef.task.TaskList;

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
     *
     * @return A string that says bye to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
