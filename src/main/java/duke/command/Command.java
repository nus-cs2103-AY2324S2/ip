package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public abstract class Command {
    protected String commandType = "";
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws DukeException;
    public boolean isExit() throws DukeException {
        return false;
    }
}
