package Commands;

import Storage.TaskList;
import UI.UserInterface;
import tasks.Task;

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
