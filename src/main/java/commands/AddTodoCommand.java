package commands;

import services.Storage;
import services.TaskList;
import services.UI;
import tasks.ToDo;

/**
 * Add Todo to task list command.
 */
public class AddTodoCommand extends AbstractCommand {
    private String name;
    public AddTodoCommand(String name) {
        this.name = name;
    }

    @Override
    public UserCommand execute(TaskList taskList, UI ui, Storage storage) {
        ToDo todo = new ToDo(this.name);
        taskList.addTask(todo);
        storage.saveTasks(taskList);
        return new UserCommand("\tAdded todo: ", "\t" + todo, taskList.getTaskSummary());
    }
}
