package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Ui;

public class MarkUndoneCommand extends Command {
    Ui ui;
    TaskList taskList;
    Storage storage;
    int indexToUnmark;
    public MarkUndoneCommand(Ui ui, TaskList taskList, Storage storage, int indexToUnmark) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        this.indexToUnmark = indexToUnmark;
    }
    @Override
    public void execute() throws DukeException {
        if(this.indexToUnmark < 1 || this.indexToUnmark > this.taskList.size()){
            throw new DukeException("Invalid task number entered.");
        }
        taskList.setAsNotDone(this.indexToUnmark - 1);
        Task targetTask = taskList.getTask(this.indexToUnmark - 1);
        this.ui.printMarkUndoneMessage(targetTask, indexToUnmark);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
