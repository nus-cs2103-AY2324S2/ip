package Duke.Command;

import Duke.Storage;
import Duke.Task.TaskList;
import Duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.displayAllTasks(taskList);
    }
}
