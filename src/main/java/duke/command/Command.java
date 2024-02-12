package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    protected String input;

    public Command(String input) {
        this.input = input;
    }

    public abstract String executeAndReply(Ui ui, TaskList tasks, Storage storage) throws DukeException;

    public boolean isBye() {
        return false;
    }
}
