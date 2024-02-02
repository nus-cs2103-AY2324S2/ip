package Command;

import java.io.IOException;
import Exceptions.DukeException;

import Utility.TaskList;
import Utility.Ui;
import Utility.Storage;

/**
 * Represents a command to delete a {@link Task} in a {@link TaskList}.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int i) {
        this.taskNumber = i;
    }

    /**
     * Deletes a task and updates the file.
     *
     * @param list the task list
     * @param ui Duke UI
     * @param s Duke Storage
     */
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.showDelete();
        try {
            list.deleteTask(taskNumber);
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
