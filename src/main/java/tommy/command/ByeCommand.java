package tommy.command;

import tommy.Ui;
import tommy.Storage;

import tommy.task.TaskList;

/**
 * Represents the command to terminate and exit the program.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.farewell();
    }

    /**
     * Returns the availability of the program to take in commands afterwards,
     * which is false after the BYE command
     *
     * @returns False, as the program terminates.
     */
    @Override
    public boolean isActive() {
        return false;
    }
}
