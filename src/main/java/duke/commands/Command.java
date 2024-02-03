package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public Command() {}

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
