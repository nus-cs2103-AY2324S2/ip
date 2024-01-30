package Blu.command;

import Blu.exception.BluException;
import Blu.storage.Storage;
import Blu.task.TaskList;
import Blu.ui.UI;

public class ListCommand extends Command {
    
    @Override
    public void execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        ui.showTaskList(taskList);
    }
}
