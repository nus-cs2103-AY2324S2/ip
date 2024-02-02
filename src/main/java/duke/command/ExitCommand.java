package duke.command;

import duke.utility.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

public class ExitCommand extends Command {
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.farewell();
    }

    public boolean isExit() {
        return true;
    };
}
