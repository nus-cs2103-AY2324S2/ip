package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;

public class GreetCommand extends Command {

    public GreetCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.greet();
    }
}
