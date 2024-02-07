package judy.commands;

import judy.storage.Storage;
import judy.ui.Ui;

public class HelpCommand extends Command{
    public static final String COMMAND_WORD = "help";
    @Override
    public void execute(Storage storage, Ui ui) {
        ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}