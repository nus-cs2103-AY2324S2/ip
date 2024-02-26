package commands;

import exceptions.ArgumentException;
import storage.TaskList;
import tasks.Task;
import ui.UserInterface;

/**
 * Represents a Command for removing a task at a specified index from the TaskList.
 */
public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean execute(TaskList list, UserInterface ui) throws ArgumentException {
        Task t = list.getTask(this.index);
        list.deleteTask(this.index);
        ui.showDelete(t);
        ui.showListSize(list.getSize());
        return false;
    }
}
