package felix.command;

import felix.exception.FelixException;
import felix.task.Task;
import felix.utils.TaskList;
import felix.utils.Ui;
import felix.utils.Storage;

public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        ui.println("Got it. I've added this task:");
        ui.println(task);
        tasks.addTask(task);
        ui.println(String.format("Now you have %d tasks in the list.", tasks.getCount()));
        storage.writeToFile(tasks);
    }
}
