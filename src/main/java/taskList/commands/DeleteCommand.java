package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;
import tasklist.tasks.Task;

public class DeleteCommand implements Command {
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
