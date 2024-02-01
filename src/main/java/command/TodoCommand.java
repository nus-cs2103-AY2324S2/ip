package command;

import data.Task;
import data.TaskList;
import data.Todo;
import data.exception.CoDriverException;
import storage.Storage;
import ui.Ui;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        Task newTask = new Todo(this.description);
        tasks.addTask(newTask);
        ui.showAddTask(newTask, tasks.size());
    }
}
