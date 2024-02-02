package Command;

import java.io.IOException;
import Exceptions.DukeException;

import Utility.TaskList;
import Utility.Ui;
import Utility.Storage;

/**
 * Represents a command to mark a {@link Task} in a {@link TaskList} as undone.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int i) {
        this.taskNumber = i;
    }

    /**
     * Marks a task as undone and updates the file.
     *
     * @param list the task list
     * @param ui Duke UI
     * @param s Duke Storage
     */
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.showUnmark();
        try {
            list.undo(this.taskNumber);
            s.storeTaskList(list.getList());
        } catch (IOException | DukeException e) {
            ui.showError(e.getMessage());
        }
        ui.listTasks(list.getList());
    }

    public boolean isExit() {
        return false;
    }
}
