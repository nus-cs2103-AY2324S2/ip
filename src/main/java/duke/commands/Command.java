package duke.commands;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.ui.Ui;
import duke.tasks.TaskList;

public class Command {

    public Command() {
    }

    public boolean execute() {
        return true;
    }

    public boolean execute(Ui ui, TaskList tasks) {
        return true;
    }

    public boolean isExit() {
        return false;
    }
}

