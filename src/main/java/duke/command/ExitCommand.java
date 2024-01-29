package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
