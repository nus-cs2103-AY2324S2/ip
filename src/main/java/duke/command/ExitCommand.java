package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;


public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.goodbyeMsg();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
