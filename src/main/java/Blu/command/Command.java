package Blu.command;

import Blu.exception.BluException;
import Blu.storage.Storage;
import Blu.task.TaskList;
import Blu.ui.UI;

public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage, UI ui) throws BluException;
}
