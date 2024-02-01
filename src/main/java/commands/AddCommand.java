package commands;

import exceptions.FileError;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

import java.sql.SQLOutput;

public class AddCommand extends Command{
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileError {
        tasks.getTaskList().add(task);
        String result = "Got it. I've added this task:\n" + task +
                String.format("Now you have %d tasks in the list\n", tasks.getTaskList().size());
        ui.showResult(result);
        storage.write(tasks.getTaskList());
    }
}
