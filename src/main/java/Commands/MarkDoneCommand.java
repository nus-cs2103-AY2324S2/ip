package Commands;

import Storage.TaskList;
import UI.UserInterface;

public class MarkDoneCommand implements Command{
    private int index;
    public MarkDoneCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        list.markTaskDone(this.index);
        ui.showMarkDone(list.getTask(this.index));
        return false;
    }
}
