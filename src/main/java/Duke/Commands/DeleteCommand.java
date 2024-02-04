package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Ui;

public class DeleteCommand extends Command {
    Ui ui;
    TaskList taskList;
    Storage storage;
    int indexToDelete;
    public DeleteCommand(Ui ui, TaskList taskList, Storage storage, int indexToDelete) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        this.indexToDelete = indexToDelete;
    }
    @Override
    public void execute() throws DukeException {
        if (taskList.size() < this.indexToDelete) {
            throw new DukeException("Invalid task index to delete!");
        }
        Task taskToBeDeleted = taskList.getTask(this.indexToDelete - 1);
        taskList.deleteTask(this.indexToDelete - 1);
        this.ui.printDeleteMessage(this.taskList.size(), taskToBeDeleted);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
