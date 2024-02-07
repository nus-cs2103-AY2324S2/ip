package tyler.command;

import tyler.task.TaskList;
import tyler.ui.Ui;
import tyler.storage.Storage;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTask(tasks);
        ui.showBye();
    }
}
