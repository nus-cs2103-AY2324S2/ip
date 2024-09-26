package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * Represents a command to mark a task as not done.
 */
public class MarkUndoneCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private int indexToUnmark;

    /**
     * Constructor for the MarkUndoneCommand class.
     *
     * @param ui The Ui object to interact with user.
     * @param taskList The taskList object to record the tasks.
     * @param storage The Storage object to save and load information.
     * @param indexToUnmark The index of task to be mark undone.
     */
    public MarkUndoneCommand(Ui ui, TaskList taskList, Storage storage, int indexToUnmark) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        this.indexToUnmark = indexToUnmark;
        assert indexToUnmark > 1 : "index to unmark should not be less than 1";
        assert indexToUnmark <= taskList.size() : "index to unmark should not be greater than list size";
    }
    @Override
    public String execute() throws DukeException {
        if (this.indexToUnmark < 1 || this.indexToUnmark > this.taskList.size()) {
            throw new DukeException("Invalid task number entered.");
        }
        taskList.setAsNotDone(this.indexToUnmark - 1);
        Task targetTask = taskList.getTask(this.indexToUnmark - 1);
        return this.ui.printMarkUndoneMessage(targetTask, indexToUnmark);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
