package mike.command;

import mike.MikeException;
import mike.Storage;
import mike.TaskList;
import mike.task.Task;
import mike.task.Todo;

/**
 * Adds a Todo to the task list.
 * @author ningc
 */
public class AddTodoCommand extends AddCommand {

    /**
     * Constructor.
     * @param description What the todo task is about.
     */
    public AddTodoCommand(String description) {
        super(description);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws MikeException {
        Task newTask = new Todo(description);
        taskList.add(newTask);
        return response(taskList, newTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "ADD TODO " + description;
    }
}
