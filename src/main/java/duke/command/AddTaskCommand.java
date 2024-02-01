package duke.command;

import duke.task.Task;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.io.IOException;

public class AddTaskCommand extends Command {
    private Task taskToBeAdded;

    public AddTaskCommand(Task task) {
        this.taskToBeAdded = task;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(taskToBeAdded);
        ui.showAddedTask(taskToBeAdded, taskList.listSize());
        try {
            storage.saveStorage(taskList.getTaskStore());
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
