package johnny.commands;

import johnny.storage.Storage;
import johnny.ui.Ui;
import johnny.tasks.TaskList;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showEnd();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
