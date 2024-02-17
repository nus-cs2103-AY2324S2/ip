package commands;

import exception.InvalidCommandException;
import objects.Task;
import objects.TaskList;
import objects.ToDo;
import view.CreatedTask;
/**
 * The CreateTodo class represents a command to create and add a todo task to the TaskList.
 * It implements the Command interface and specifies the execution behavior for creating a todo task.
 */
public class CreateTodo implements Command {

    /** The TaskList where the todo task will be added. */
    private final TaskList tasks;

    /** The input string containing the name of the new todo task. */
    private final String input;

    /**
     * Constructs a CreateTodo command with the specified TaskList and input string.
     *
     * @param tasks The TaskList where the todo task will be added.
     * @param input The input string containing the name of the new todo task.
     */
    public CreateTodo(TaskList tasks, String input) {
        this.tasks = tasks;
        this.input = input;
    }

    /**
     * Executes the CreateTodo command by creating a new todo task, adding it to the TaskList,
     * and displaying a confirmation message.
     *
     * @return String
     * @throws InvalidCommandException If there is an issue with the todo task information provided.
     */
    @Override
    public String execute() throws InvalidCommandException {
        Task t = new ToDo(input);
        tasks.addTask(t);

        return CreatedTask.display(this.tasks, t);
    }
}
