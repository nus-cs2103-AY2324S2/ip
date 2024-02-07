package judy.commands;

import judy.storage.Storage;
import judy.ui.Ui;

public class GreetCommand extends Command {
    @Override
    public void execute(Storage storage, Ui ui) {
        ui.showWelcome();
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
