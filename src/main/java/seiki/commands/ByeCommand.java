package seiki.commands;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.storage.Storage;
import seiki.ui.Ui;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        ui.showEnd();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
