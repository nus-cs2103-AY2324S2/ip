package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class CommandBye extends Command {
    public CommandBye() {}

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }
}
