package commands;

import helpers.TaskList;
import helpers.Ui;

public class ListCommand extends Command{
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.listTasks(taskList);
    }
}
