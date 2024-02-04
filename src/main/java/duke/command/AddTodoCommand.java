package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import duke.exception.InvalidArgumentException;

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
