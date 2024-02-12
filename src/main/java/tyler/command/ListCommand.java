package tyler.command;

import tyler.task.TaskList;
import tyler.storage.Storage;
import tyler.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks);
    }
}
