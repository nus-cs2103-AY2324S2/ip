package commands;

import storage.TaskList;
import ui.UserInterface;

/**
 * Represents a Command to display the current state of the TaskList.
 */
public class ListCommand implements Command {
    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        ui.showList(list.toString());
        return false;
    }
}
