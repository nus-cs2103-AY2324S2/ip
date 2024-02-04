package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

/**
 * Represents the command to terminate and exit the program.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.farewell();
    }

    /**
     * Returns the availability of the program to take in commands afterwards,
     * which is false after the BYE command
     *
     * @returns False, as the program terminates.
     */
    @Override
    public boolean getActive() {
        return false;
    }
}
