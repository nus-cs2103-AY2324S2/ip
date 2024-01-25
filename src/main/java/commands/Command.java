package commands;

import services.Storage;
import services.TaskList;
import services.UI;

public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui, Storage storage);

}
