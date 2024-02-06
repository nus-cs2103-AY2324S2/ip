package Commands;

import Storage.TaskList;
import UI.UserInterface;

public class ListCommand implements Command{
    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        ui.showList(list.toString());
        return false;
    }
}
