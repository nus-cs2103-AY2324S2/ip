package commands;

import exceptions.ArgumentException;
import storage.TaskList;
import ui.UserInterface;

/**
 * Represents a Command for updating a task at a specified index in the TaskList.
 */
public class UpdateCommand implements Command {
    private int index;
    private String details;
    public UpdateCommand(int index, String details) {
        this.index = index;
        this.details = details;
    }
    @Override
    public boolean execute(TaskList list, UserInterface ui) throws ArgumentException {
        list.updateTask(this.index, this.details);
        ui.showUpdate(list.getTask(this.index));
        return false;
    }
}
