package liv.processor;

import liv.container.TaskList;
import liv.task.TodoTask;
import liv.ui.Ui;

public class TodoCommand extends Command {
    private TodoTask todo;

    public TodoCommand(TodoTask todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(todo);
        Ui.displayTodoCommand(todo);
    }

    @Override
    public boolean hasChangedData() {
        return true;
    }
}
