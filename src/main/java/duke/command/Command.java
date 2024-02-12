package duke.command;

//import java.io.IOException;

import duke.JamieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An abstract class representing a command to be executed.
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Executes the command.
     *
     * @param tasks   The task list.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the task list.
     * @return
     * @throws JamieException If there is an error while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JamieException;

    /**
     * Marks the command as an exit command.
     */
    public void exit() {
        this.isExit = !this.isExit;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false; // Default implementation, can be overridden by subclasses
    }
}


