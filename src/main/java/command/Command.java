package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A superclass and abstract class.
 */
public abstract class Command {

    public boolean isExit;

    /**
     * Creates a constructor.
     */
    public Command() {
        isExit = false;
    }

    /**
     * An abstract method.
     *
     * @param tasks the Task Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @throws DukeException If there is an error.
     */
    public abstract void excuteCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Changes the status of the isExit to true.
     */
    public void confirmExit() {
        this.isExit = true;
    }

    /**
     * Gets the status of the isExit.
     * @return isExit.
     */
    public boolean getIsExit() {
        return this.isExit;
    }

}
