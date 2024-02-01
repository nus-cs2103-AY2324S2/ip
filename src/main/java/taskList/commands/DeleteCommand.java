package taskList.commands;

import taskList.Storage;
import taskList.Task;
import taskList.TaskList;
import taskList.Ui;

public class DeleteCommand implements Command{

    protected int indexNo;

    public DeleteCommand(int indexNo) {
        this.indexNo = indexNo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task deletedTask = taskList.deleteTask(indexNo);
        ui.showDeletedTaskMessage(deletedTask, taskList.size());        
    }
    
}