package duke.command;

import java.io.IOException;

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
     * Executes the command within the context of the Duke application.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage to persist task changes.
     * @return A message indicating the outcome of the execution.
     * @throws JamieException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws JamieException, IOException;

    /**
     * Marks the command as an exit command to signal the application to terminate.
     */
    public void markAsExit() {
        this.isExit = true;
    }

}


