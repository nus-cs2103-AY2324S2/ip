package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.gui.Ui;

/**
 * Represents an action to show the exit message. An <code>ExitCommand</code> object corresponds to
 * a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to show the exit message.
     *
     * @param ui The UI to display the exit message.
     * @param storage The storage during which the program exits.
     * @param taskList The task list during which the program exits.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        return ui.getExitResponse();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
