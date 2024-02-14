package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public abstract class Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        throw new UnsupportedOperationException("This should only be implemented by child classes");
    }
    public boolean isExit() {
        throw new UnsupportedOperationException("This should only be implemented by child classes");
    }

}
