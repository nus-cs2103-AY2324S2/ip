package commands;

import exceptions.ChaterpillarException;
import tasks.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList task, Ui ui, Storage storage) throws ChaterpillarException, IOException;
    public boolean hasExited() {
        return false;
    }
}
