package zack.commands;

import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getAllTasks());
    }
}
