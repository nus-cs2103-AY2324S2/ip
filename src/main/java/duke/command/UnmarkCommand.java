package duke.command;

import java.io.IOException;
import duke.exceptions.DukeException;

import duke.utility.Ui;
import duke.utility.TaskList;
import duke.utility.Storage;

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
