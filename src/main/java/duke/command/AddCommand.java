package duke.command;

import duke.utility.Ui;
import duke.utility.TaskList;
import duke.utility.Storage;

import duke.task.Task;
import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task t) {
        this.task = t;
    }

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
