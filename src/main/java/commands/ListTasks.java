package commands;

import exceptions.InvalidIndexException;
import mainUtils.Storage;
import mainUtils.Ui;
import tasks.TaskList;

public class ListTasks extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException {
        taskList.displayTasks();
    }
}
