package commands;

import util.Ui;
import util.TaskList;
import util.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listTasks();
    }
}