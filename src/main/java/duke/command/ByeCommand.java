package duke.command;

import duke.Storage;
import duke.Ui;

import duke.task.TaskList;

/**
 * Represents the command to terminate and exit the program.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        isActive = false;
        ui.farewell();
    }
}
