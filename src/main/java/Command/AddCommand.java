package Command;

import Utility.TaskList;
import Utility.Ui;
import Utility.Storage;
import Task.Task;
import java.io.IOException;

/**
 * Represents a command to add a {@link Task} in a {@link TaskList}.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task t) {
        this.task = t;
    }

    /**
     * Adds a task and updates the file.
     *
     * @param list the task list
     * @param ui Duke UI
     * @param s Duke Storage
     */
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.showAdd();
        ui.showTask(task);
        list.addTask(task);
        ui.showNumOfTask(list.getList().size());
        try {
            s.storeTaskList(list.getList());
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
