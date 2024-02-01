package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class ExitTaskCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        super.exit();
    }

    //@Override
    @Override
    public boolean isExit() {
        return true; // This will signal that it's time to exit the application
    }
}