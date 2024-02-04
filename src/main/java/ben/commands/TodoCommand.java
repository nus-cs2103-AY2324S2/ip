package ben.commands;

import ben.storage.Storage;
import ben.tasks.Task;
import ben.tasks.TaskList;
import ben.tasks.Todo;
import ben.ui.Ui;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTodo = new Todo(false, description);
        tasks.addTask(newTodo);

        ui.showAddedTaskMessage();
        Ui.show(newTodo.toString());
        ui.showCurrNoOfTasks(tasks);
    }
}
