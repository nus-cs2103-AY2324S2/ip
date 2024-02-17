package commands;

import storage.TaskList;
import tasks.Task;
import ui.UserInterface;

/**
 * Represents a Command for adding a new task to the TaskList.
 */
public class AddCommand implements Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        list.addTask(this.task);
        ui.showAdd(this.task);
        ui.showListSize(list.getSize());
        return false;
    }
}
