package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * Represents a command to mark a task as done.
 */
public class MarkDoneCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private int indexToMark;

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
        assert indexToMark > 1 : "index to mark should not be less than 1";
        assert indexToMark <= taskList.size() : "index to mark should not be greater than list size";
    }
    @Override
    public String execute() throws DukeException {
        if (this.indexToMark < 1 || this.indexToMark > this.taskList.size()) {
            throw new DukeException("Invalid task number entered.");
        }
        this.taskList.setAsDone(this.indexToMark - 1);
        Task targetTask = taskList.getTask(this.indexToMark - 1);
        return this.ui.printMarkDoneMessage(targetTask, this.indexToMark);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
