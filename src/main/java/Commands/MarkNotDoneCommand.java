package Commands;

import Storage.TaskList;
import UI.UserInterface;

public class MarkNotDoneCommand implements Command{
    private int index;
    public MarkNotDoneCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        list.markTaskNotDone(this.index);
        ui.showMarkNotDone(list.getTask(this.index));
        return false;
    }
}
