package Commands;

import Storage.TaskList;
import UI.UserInterface;

public class ExitCommand implements Command{

    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        return true;
    }
}
