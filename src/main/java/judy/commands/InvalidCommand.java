package judy.commands;

import judy.storage.Storage;
import judy.ui.Ui;

public class InvalidCommand extends Command {
    private final String errorMessage;

    public InvalidCommand() {
        this.errorMessage = " Sorry! I don't know what that means :c ";
    }

    @Override
    public void execute(Storage storage, Ui ui) {
        ui.showError(errorMessage);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
