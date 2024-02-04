package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkDoneCommand extends Command {
    Ui ui;
    TaskList taskList;
    Storage storage;
    int indexToMark;

    /**
     * Constructor for the MarkDoneCommand class.
     *
     * @param ui The Ui object to interact with user.
     * @param taskList The taskList object to record the tasks.
     * @param storage The Storage object to save and load information.
     * @param indexToMark The index of task to be mark done.
     */
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
