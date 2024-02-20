package tommy.command;

import tommy.Storage;
import tommy.Ui;
import tommy.exception.InvalidArgumentException;
import tommy.task.Task;
import tommy.task.TaskList;
import tommy.task.Todo;

/**
 * Represents the command to add a Todo task to the taskList.
 */
public class AddTodoCommand extends Command {

    private String description;

    /**
     * Constructor for an AddToDo command,
     * which initialises the command with its task description.
     *
     * @param description Description of the Todo task.
     * @throws InvalidArgumentException If the description is empty.
     */

    public AddTodoCommand(String description) throws InvalidArgumentException {
        if (description == null || description.trim().isBlank()) {
            throw new InvalidArgumentException("TODO");
        }
        this.description = description;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {
            Task todo = new Todo(this.description);

            taskList.addTask(todo);
            Storage.save(taskList);
            return Ui.displayNewTask(todo, taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("TODO");
        }
    }
}
