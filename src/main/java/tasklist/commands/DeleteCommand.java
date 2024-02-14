package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;
import tasklist.tasks.Task;

/** Represents a command to delete tasks. Implements the Command interface. */
public class DeleteCommand implements Command {
    protected int indexNo;

    public DeleteCommand(int indexNo) {
        this.indexNo = indexNo;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task deletedTask = taskList.deleteTask(indexNo);
            return ui.showDeletedTaskMessage(deletedTask, taskList.size());
        } catch (IndexOutOfBoundsException e) {
            return(e.getMessage());
        }
    }
}
