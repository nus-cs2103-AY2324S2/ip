package commands;

import storage.TaskList;
import ui.UserInterface;

/**
 *  Represents a Command to display all Tasks in the TaskList containing a given String.
 */
public class FindCommand implements Command {

    private String match;

    public FindCommand(String match) {
        this.match = match;
    }
    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        ui.showFound(list.findTasks(match));
        return false;
    }
}
