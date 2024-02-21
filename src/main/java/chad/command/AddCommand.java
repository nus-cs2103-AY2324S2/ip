package chad.command;

import java.io.IOException;

import chad.task.Task;
import chad.utility.Storage;
import chad.utility.TaskList;
import chad.utility.Ui;


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
     * @param ui Chad UI
     * @param s Chad Storage
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
