package commands;
import exceptions.DukeException;
import tasks.Task;
import ui.Ui;
import tasks.TaskList;

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

