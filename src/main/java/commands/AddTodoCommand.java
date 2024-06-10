package commands;

import exceptions.DukeException;
import services.Storage;
import services.TaskList;
import services.UI;
import tasks.ToDo;

/**
 * Add Todo to task list command.
 */
public class AddTodoCommand extends AbstractCommand {
    private String name;
    /**
     * AddTodoCommand constructor.
     */
    public AddTodoCommand(String name) {
        this.name = name;
    }

    @Override
    public UserCommand execute(TaskList taskList, UI ui, Storage storage) {
        ToDo todo = new ToDo(this.name);
        try {
            taskList.addTask(todo);
        } catch (DukeException e) {
            return new UserCommand("\tA todo with the same name already exists.");
        }
        storage.saveTasks(taskList);
        return new UserCommand("\tAdded todo: ", "\t" + todo, taskList.getTaskSummary());
    }
}
