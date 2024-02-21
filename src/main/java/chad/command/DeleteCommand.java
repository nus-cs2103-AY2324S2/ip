package chad.command;

import java.io.IOException;

import chad.exceptions.ChadException;
import chad.task.Task;
import chad.utility.Storage;
import chad.utility.TaskList;
import chad.utility.Ui;

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
     * @param ui Chad UI
     * @param s Chad Storage
     */
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.showDelete();
        try {
            list.deleteTask(taskNumber);
            s.storeTaskList(list.getList());
        } catch (IOException | ChadException e) {
            ui.showError(e.getMessage());
        }
        ui.listTasks(list.getList());
    }

    public boolean isExit() {
        return false;
    }
}
