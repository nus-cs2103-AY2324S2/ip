package commands;

import java.io.IOException;

import tasks.TaskList;
import ui.Ui;
import storage.Storage;
public abstract class Command {
    public abstract void execute(TaskList task, Ui ui, Storage storage) throws IOException;
    public boolean hasExited() {
        return false;
    }
}
