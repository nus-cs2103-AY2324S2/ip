package raphael.command;

import raphael.task.TaskList;
import raphael.ui.Ui;
import raphael.storage.Storage;
import raphael.exception.DukeException;
public abstract class Command {
    public static enum TYPE {
        CHECK,
        UNCHECK,
        DELETE
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public boolean isExit() {
        return false;
    }
}
