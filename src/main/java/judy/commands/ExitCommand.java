package judy.commands;

import judy.storage.Storage;
import judy.ui.Ui;

public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(Storage storage, Ui ui) {
        ui.showGoodbye();
    }
    @Override public boolean isExit() {
        return true;
    }
}
