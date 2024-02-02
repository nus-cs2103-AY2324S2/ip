package duke.command;

import duke.utility.Ui;
import duke.utility.TaskList;
import duke.utility.Storage;

public class ListCommand extends Command {
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.listTasks(list.getList());
    }

    public boolean isExit() {
        return false;
    }
}
