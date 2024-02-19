package judy.commands;

import judy.storage.Storage;
import judy.ui.Ui;

/**
 * The HelpCommand class represents a command to display available commands in the user interface.
 */
public class HelpCommand extends Command{
    public static final String COMMAND_WORD = "help";

    /**
     * Executes the command by displaying help information in the user interface.
     *
     * @param storage The storage component (not used in this implementation).
     * @param ui      The user interface component for displaying messages to the user.
     */
    @Override
    public void execute(Storage storage, Ui ui) {
        ui.showHelp();
    }

    /**
     * Indicates whether this command represents an exit command.
     * In this case, always returns false as displaying help information does not trigger an exit.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}