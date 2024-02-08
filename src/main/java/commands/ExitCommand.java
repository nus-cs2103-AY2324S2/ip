package commands;

import storage.TaskList;
import ui.UserInterface;

/**
 * Command to signal the end of the program
 */
public class ExitCommand implements Command{

    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        return true;
    }
}
