package command;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;



/**
 * A superclass and abstract class.
 */
public abstract class Command {

    /** The status of the system. */
    private boolean isExit;

    /**
     * Creates a constructor.
     */
    public Command() {
        isExit = false;
    }


    /**
     * Executes some actions by the subclasses.
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @return The response from Andelu.
     * @throws AndeluException If there is an error.
     */
    public abstract String executeCommand(TaskList tasks, Ui ui, Storage storage) throws AndeluException;

    /**
     * Changes the status of the isExit to true.
     */
    public void confirmExit() {
        this.isExit = true;
    }

    /**
     * Gets the status of the isExit.
     *
     * @return isExit.
     */
    public boolean getIsExit() {
        return this.isExit;
    }

}
