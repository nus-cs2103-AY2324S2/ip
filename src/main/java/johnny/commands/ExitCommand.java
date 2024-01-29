package johnny.commands;

import johnny.storage.Storage;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

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
