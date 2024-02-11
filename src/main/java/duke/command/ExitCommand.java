package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.ui.Ui;

public class ExitCommand implements Command {

    public void execute(TaskList tasks, Ui ui) throws DukeException {
    }

    public boolean isExit() {
        return true;
    }
}
