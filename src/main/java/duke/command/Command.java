package duke.command;

import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
