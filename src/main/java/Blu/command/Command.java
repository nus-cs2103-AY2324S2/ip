package blu.command;

import blu.exception.BluException;
import blu.storage.Storage;
import blu.task.TaskList;
import blu.ui.UI;

public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage, UI ui) throws BluException;
}
