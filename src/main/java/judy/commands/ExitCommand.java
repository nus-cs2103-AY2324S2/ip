package judy.commands;

import judy.storage.Storage;
import judy.ui.Ui;

/**
 * The ExitCommand class represents a command to close and terminate the application.
 */
public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "bye";

    /**
     * Executes the command by displaying a goodbye message.
     *
     * @param storage The storage component (not used in this implementation).
     * @param ui      The user interface component for displaying messages to the user.
     */
    @Override
    public void execute(Storage storage, Ui ui) {
        ui.showGoodbye();
    }

    /**
     * Indicates whether this command represents an exit command.
     * In this case, always returns true as the purpose of the command is to exit the application.
     *
     * @return Always returns true.
     */
    @Override public boolean isExit() {
        return true;
    }
}
