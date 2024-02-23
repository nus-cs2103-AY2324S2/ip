package commands;

import exceptions.DukeException;
import services.Storage;
import services.TaskList;
import services.UI;


/**
 * Finds a task from the task list.
 */
public class FindCommand extends AbstractCommand {
    private String name;
    public FindCommand(String name) {
        this.name = name;
    }

    @Override
    public UserCommand execute(TaskList taskList, UI ui, Storage storage) {
        try {
            TaskList foundTasks = taskList.findTasks(this.name);
            return new UserCommand("\tHere are the list of tasks: ", foundTasks.listTasks());
        } catch (DukeException e) {
            return new UserCommand("\tNo task with " + this.name + " found");
        }
    }
}
