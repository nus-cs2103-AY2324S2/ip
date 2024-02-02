package duke.command;

import java.io.IOException;
import duke.exceptions.DukeException;

import duke.utility.Ui;
import duke.utility.TaskList;
import duke.utility.Storage;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int i) {
        this.taskNumber = i;
    }

    public void execute(TaskList list, Ui ui, Storage s) {
        ui.showDelete();
        try {
            list.deleteTask(taskNumber);
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
