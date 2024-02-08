package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private int indexToDelete;

    /**
     * Constructor for the DeleteCommand class.
     *
     * @param ui The Ui object to interact with user.
     * @param taskList The taskList object to record the tasks.
     * @param storage The Storage object to save and load information.
     * @param indexToDelete The index of task to be deleted.
     */
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
