package blu.command;

import blu.exception.BluException;
import blu.storage.Storage;
import blu.task.TaskList;
import blu.ui.UI;

public class ListCommand extends Command {
    
    @Override
    public void execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        ui.showTaskList(taskList);
    }
}
