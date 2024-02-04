package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

public class ByeCommand extends Command {

    public ByeCommand() {
        this.isActive = false;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.farewell();
    }
}
