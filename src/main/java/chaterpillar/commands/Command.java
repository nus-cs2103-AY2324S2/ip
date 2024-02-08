package chaterpillar.commands;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList task, Ui ui, Storage storage) throws ChaterpillarException, IOException;
    public boolean hasExited() {
        return false;
    }
}
