package eggy.command;

import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
