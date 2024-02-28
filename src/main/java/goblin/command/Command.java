package goblin.command;

import goblin.Storage;
import goblin.TaskList;
import goblin.Ui;
import goblin.exception.OrkException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws OrkException;

    public boolean isWorking() {
        return true;
    }
}
