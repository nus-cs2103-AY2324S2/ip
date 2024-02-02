package raphael.command;

import raphael.task.TaskList;
import raphael.ui.Ui;
import raphael.storage.Storage;

public abstract class Command {
    public static enum TYPE {
        CHECK,
        UNCHECK,
        DELETE
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws raphael.exception.RaphaelException;
    public boolean isExit() {
        return false;
    }
}
