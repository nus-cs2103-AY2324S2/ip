package commands;

import exceptions.ArgumentException;
import storage.TaskList;
import ui.UserInterface;

/**
 * represents a Command for marking a task as done at a specified index in the TaskList.
 */
public class MarkDoneCommand implements Command {
    private int index;
    public MarkDoneCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean execute(TaskList list, UserInterface ui) throws ArgumentException {
        list.markTaskDone(this.index);
        ui.showMarkDone(list.getTask(this.index));
        return false;
    }
}
