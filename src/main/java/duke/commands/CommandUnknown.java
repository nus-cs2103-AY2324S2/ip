package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandUnknown extends Command {
    public CommandUnknown() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("OOPS!!! I don't understand that command, try again later.");
    }
}
