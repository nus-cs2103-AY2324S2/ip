package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand implements Command {

    public String execute(TaskList tasks, Ui ui) throws DukeException {
        return "";
    }

    public boolean isExit() {
        return true;
    }
}
