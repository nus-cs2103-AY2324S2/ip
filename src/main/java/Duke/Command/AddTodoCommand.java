package Duke.Command;

import Duke.Exception.*;
import Duke.Task.TaskList;
import Duke.Task.Task;
import Duke.Task.Todo;
import Duke.Ui;
import Duke.Storage;
import Duke.Exception.InvalidArgumentException;

public class AddTodoCommand extends Command {
    String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {
            Task todo = new Todo(this.description);
            taskList.addTask(todo);
            Storage.save(taskList);
            Ui.displayNewTask(todo, taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("TODO");
        }

    }
}
