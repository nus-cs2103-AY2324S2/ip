package Duke.commands;
import Duke.util.TaskList;
import Duke.util.UI;
import Duke.util.Storage;
import Duke.exceptions.DukeException;

public abstract class Commands {
    public abstract boolean execute(TaskList tasks, UI ui, Storage s) throws DukeException;
}

