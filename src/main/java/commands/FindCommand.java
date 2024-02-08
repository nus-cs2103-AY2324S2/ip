package commands;

import storage.TaskList;
import ui.UserInterface;

public class FindCommand implements Command {

    String match;

    public FindCommand(String match) {
        this.match = match;
    }
    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        ui.showFound(list.findTasks(match));
        return false;
    }
}
