package duke.command;

import duke.TaskList;
import duke.task.Task;
import duke.Storage;
import duke.Ui;

import java.io.IOException;

public class AddTaskCommand extends Command {
    private final Task taskToAdd;

    public AddTaskCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(taskToAdd);
        storage.save(tasks);
        ui.showAddTaskMessage(taskToAdd, tasks);
    }

}