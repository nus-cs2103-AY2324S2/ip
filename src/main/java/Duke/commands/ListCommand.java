package Duke.commands;

import Duke.util.TaskList;
import Duke.util.UI;
import Duke.util.Storage;
public class ListCommand extends Commands {
    public ListCommand() {
        super();
    }
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage s) {
        ui.displayList(tasks.list());
        return false;
    }
}
