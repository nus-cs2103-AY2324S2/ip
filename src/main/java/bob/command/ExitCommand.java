package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.showExit();
    }

    @Override
    public boolean checkExit() {
        return true;
    }
}
