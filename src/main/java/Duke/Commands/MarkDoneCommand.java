package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Ui;

public class MarkDoneCommand extends Command {
    Ui ui;
    TaskList taskList;
    Storage storage;
    int indexToMark;
    public MarkDoneCommand(Ui ui, TaskList taskList, Storage storage, int indexToMark) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        this.indexToMark = indexToMark;
    }
    @Override
    public void execute() throws DukeException {
        if (this.indexToMark < 1 || this.indexToMark > this.taskList.size()) {
            throw new DukeException("Invalid task number entered.");
        }
        this.taskList.setAsDone(this.indexToMark - 1);
        Task targetTask = taskList.getTask(this.indexToMark - 1);
        this.ui.printMarkDoneMessage(targetTask, this.indexToMark);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
