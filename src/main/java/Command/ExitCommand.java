package Command;

import Utility.TaskList;
import Utility.Ui;
import Utility.Storage;

public class ExitCommand extends Command {
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.farewell();
    }

    public boolean isExit() {
        return true;
    };
}
