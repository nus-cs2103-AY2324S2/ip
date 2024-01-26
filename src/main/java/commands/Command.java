package commands;

import services.Storage;
import services.TaskList;
import services.UI;

/**
 * Represents an executable command as an abstract class.
 */
public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui, Storage storage);

}
