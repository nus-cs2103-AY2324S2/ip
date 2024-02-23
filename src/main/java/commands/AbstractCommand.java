package commands;

import exceptions.DukeException;
import services.Storage;
import services.TaskList;
import services.UI;

/**
 * Represents an executable command as an abstract class.
 */
public abstract class AbstractCommand {
    public abstract UserCommand execute(TaskList taskList, UI ui, Storage storage);
}
