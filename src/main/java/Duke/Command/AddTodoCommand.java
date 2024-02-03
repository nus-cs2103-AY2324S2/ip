package Duke.Command;

import Duke.Task.TaskList;
import Duke.Task.Task;
import Duke.Task.Todo;
import Duke.Ui;
import Duke.Storage;

public class AddTodoCommand extends Command {
    Task task;

    public AddTodoCommand(String description) {
        this.task = new Todo(description);
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws IllegalArgumentException {
        try {
            Task todo = this.task;
            taskList.addTask(todo);
            Storage.save(taskList);
            Ui.displayNewTask(todo, taskList);
        } catch (IllegalArgumentException e) {
            System.out.println("illegal argument exception");
        }
    }






}
