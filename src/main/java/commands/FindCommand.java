package commands;

import exceptions.DukeException;
import services.Storage;
import services.TaskList;
import services.UI;


/**
 * Finds a task from the task list.
 */
public class FindCommand extends Command {
    private String name;

    public FindCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            taskList.findTasks(this.name);
        } catch (DukeException e) {
            System.out.println("\tNo task with " + this.name + " found");
        }
    }
}
