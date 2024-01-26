package commands;

import services.Storage;
import services.TaskList;
import services.UI;
import tasks.ToDo;

/**
 * Add Todo to task list command.
 */
public class AddTodoCommand extends Command {
    private String name;
    public AddTodoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ToDo todo = new ToDo(this.name);
        taskList.addTask(todo);
        storage.saveTasks(taskList);
    }
}
