package Command;

import java.io.IOException;
import Exceptions.DukeException;

import Utility.TaskList;
import Utility.Ui;
import Utility.Storage;

public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int i) {
        this.taskNumber = i;
    }
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.showUnmark();
        try {
            list.undo(this.taskNumber);
            s.storeTaskList(list.getList());
        } catch (IOException | DukeException e) {
            ui.showError(e.getMessage());
        }
        ui.listTasks(list.getList());
    }

    public boolean isExit() {
        return false;
    }
}
