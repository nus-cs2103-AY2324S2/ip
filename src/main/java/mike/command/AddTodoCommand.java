package mike.command;

import mike.MikeException;
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
    public void execute(TaskList taskList) throws MikeException {
        Task newTask = new Todo(description);
        taskList.add(newTask);
        respond(taskList, newTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
