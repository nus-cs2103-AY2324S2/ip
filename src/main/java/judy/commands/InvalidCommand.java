package judy.commands;

import judy.storage.Storage;
import judy.ui.Ui;

/**
 * The InvalidCommand class represents a command to handle invalid or unrecognized user commands.
 * It displays an error message in the user interface.
 */

public class InvalidCommand extends Command {
    private final String errorMessage; // The error message to be displayed

    /**
     * Constructs an InvalidCommand with a default error message.
     * The message is to be displayed and inform user that the command is not recognized.
     */
    public InvalidCommand() {
        this.errorMessage = " Sorry! I don't know what that means :c ";
    }

    /**
     * Executes the command by displaying the error message in the user interface.
     *
     * @param storage The storage component (not used in this implementation).
     * @param ui      The user interface component for displaying messages to the user.
     */
    @Override
    public void execute(Storage storage, Ui ui) {
        ui.showError(errorMessage);
    }

    /**
     * Indicates whether this command represents an exit command.
     * In this case, always returns false as handling an invalid command does not trigger an exit.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
