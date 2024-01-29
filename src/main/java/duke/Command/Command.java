package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.Tasks.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
