package chad.command;

import java.io.IOException;

import chad.exceptions.ChadException;
import chad.task.Task;
import chad.utility.Storage;
import chad.utility.TaskList;
import chad.utility.Ui;

/**
 * Represents a command to mark a {@link Task} in a {@link TaskList} as done.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int i) {
        this.taskNumber = i;
    }

    /**
     * Marks task as done and update the file.
     *
     * @param list the task list
     * @param ui Chad UI
     * @param s Chad Storage
     */
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.showMark();
        try {
            list.markDone(taskNumber);
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
