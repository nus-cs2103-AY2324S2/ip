package mike.command;

import mike.MikeException;
import mike.TaskList;
import mike.task.Task;
import mike.task.Todo;

public class AddTodoCommand extends AddCommand {
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
