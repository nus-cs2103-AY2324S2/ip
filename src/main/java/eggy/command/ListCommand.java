package eggy.command;

import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            ui.printTaskListEmpty();
        } else {
            ui.printTaskList(tasks);
        }
    }
}
