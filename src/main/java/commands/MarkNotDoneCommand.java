package commands;

import storage.TaskList;
import ui.UserInterface;

/**
 * Command for marking a task as not done at a specified index from the TaskList
 */
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
