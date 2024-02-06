package commands;

import storage.TaskList;
import ui.UserInterface;

public class ExitCommand implements Command{

    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        return true;
    }
}
