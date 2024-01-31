package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;
import duke.utils.DukeException;
public abstract class Command {
    public abstract void execute(TaskList tasks, TextUi ui, Storage storage) throws DukeException;
    public boolean isExit() {
        return false;
    }
}

