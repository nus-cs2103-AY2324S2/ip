package duke.command;

import duke.DukeException;
import duke.task.Storage;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
    public abstract boolean isExit();
}
