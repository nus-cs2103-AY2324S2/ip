package duke.commands;

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
     * @param tasklist Tasklist used for the command
     * @param ui       UI used for the command
     */
    public abstract void execute(TaskList tasklist, Ui ui);
}
