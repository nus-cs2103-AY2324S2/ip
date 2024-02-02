package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    int taskNumber;
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String t = tasks.unmark(this.taskNumber);
        ui.showResult("Nice, I've marked this duke.task as done:");
        ui.showResult(t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
