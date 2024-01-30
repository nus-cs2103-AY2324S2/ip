package command;

import exception.BluException;
import storage.Storage;
import task.TaskList;
import ui.UI;

public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage, UI ui) throws BluException;
}
