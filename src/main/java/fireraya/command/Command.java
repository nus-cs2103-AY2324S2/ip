package fireraya.command;

import fireraya.exception.FirerayaException;
import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;

/**
 * Class to handle all the commands given in the program.
 *
 * This class acts as a superclass for all other command classes.
 */
public class Command {

    /**
     * Executes the task by processing the command and updating relevant fields.
     * This method is meant to be overridden by its child classes.
     *
     * @param tasks the Tasklist of program.
     * @param ui the Ui of the program.
     * @param storage the storage of the program.
     * @return String representing the message to be displayed to the user.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FirerayaException {
        return "Not overridden";
    }

    /**
     * Method to check the exit status of the program.
     *
     * @return Boolean to check if the program should terminate. It is false by default.
     */
    public boolean isExit() {
        return false;
    }
}
