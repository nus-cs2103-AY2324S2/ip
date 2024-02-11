package tommy.command;

import tommy.Ui;
import tommy.Storage;

import tommy.task.Task;
import tommy.task.TaskList;
import tommy.task.Todo;

import tommy.exception.InvalidArgumentException;

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
     */
    public AddTodoCommand(String description) throws InvalidArgumentException {
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
