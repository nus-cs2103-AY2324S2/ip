package commands;

import exceptions.ArgumentException;
import storage.TaskList;
import ui.UserInterface;

/**
 * Represents a Command for marking a task as not done at a specified index in the TaskList.
 */
public class MarkNotDoneCommand implements Command {
    private int index;
    public MarkNotDoneCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean execute(TaskList list, UserInterface ui) throws ArgumentException {
        list.markTaskNotDone(this.index);
        ui.showMarkNotDone(list.getTask(this.index));
        return false;
    }
}
