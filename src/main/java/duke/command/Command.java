package duke.command;

import duke.TaskList;
import duke.TextUi;
import duke.Storage;
import duke.DukeException;
public abstract class Command {
    public abstract void execute(TaskList tasks, TextUi ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
