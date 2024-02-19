package judy.commands;

import judy.storage.Storage;
import judy.ui.Ui;

/**
 * The GreetCommand class represents a command to display a welcome message.
 */
public class GreetCommand extends Command {

    /**
     * Executes the command by displaying a welcome message in the user interface.
     *
     * @param storage The storage component (not used in this implementation).
     * @param ui      The user interface component for displaying messages to the user.
     */
    @Override
    public void execute(Storage storage, Ui ui) {
        ui.showWelcome();
    }

    /**
     * Indicates whether this command represents an exit command.
     * In this case, always returns false as displaying a welcome message does not trigger an exit.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
