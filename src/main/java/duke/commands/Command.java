package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * The Command class defines a command that can be used in the Duke application.
 *
 * @author Ryan NgWH
 */
public abstract class Command {
    /**
     * Flag indicating if command is an exit command
     */
    private boolean isExit;

    /**
     * Creates a new Command
     *
     * @param isExit Flag if command is an exist command
     */
    protected Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Check if the command is an exit command
     *
     * @return True if the command is an exist command, false otherwise
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    /**
     * Indicates whether some other object is "equal to" this command
     *
     * @param obj Object to be checked against
     *
     * @return True if equal, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Command) {
            Command command = (Command) obj;

            return this.isExit == command.isExit;
        }

        return false;
    }
}
