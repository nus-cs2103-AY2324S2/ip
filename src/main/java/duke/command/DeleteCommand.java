package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    int taskNumber;
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String t = tasks.delete(this.taskNumber);
        ui.showResult("Ok, I've removed this duke.task:");
        ui.showResult(t);
        ui.showResult("Now you have " + tasks.size() + " task(s) in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
