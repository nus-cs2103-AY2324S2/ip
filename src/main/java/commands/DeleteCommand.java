package commands;

import storage.TaskList;
import ui.UserInterface;
import tasks.Task;

/**
 * Command for removing a task at a specified index from the TaskList
 */
public class DeleteCommand implements Command{
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        Task t = list.getTask(this.index);
        list.deleteTask(this.index);
        ui.showDelete(t);
        ui.showListSize(list.getSize());
        return false;
    }
}
