package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public abstract void run(TaskList taskList, Ui ui, Storage storage) throws IOException, CommandException;

    public boolean isExit() {
        return false;
    }
}
