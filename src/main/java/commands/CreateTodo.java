package commands;

import exception.InvalidCommandException;
import objects.Task;
import objects.TaskList;
import objects.ToDos;
import view.CreatedTask;
import view.EncaseLines;

import static utils.InputUtil.getDetails;

public class CreateTodo implements Command{
    private final TaskList tasks;
    private final String input;

    public CreateTodo(TaskList tasks, String input) {
        this.tasks = tasks;
        this.input = input;
    }


    @Override
    public void execute() throws InvalidCommandException {
        Task t = new ToDos(input);
        tasks.addTask(t);

        CreatedTask.display(this.tasks, t);
    }
}
