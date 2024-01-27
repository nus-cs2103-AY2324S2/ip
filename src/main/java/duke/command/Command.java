package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An abstraction for the commands of the program.
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Executes the command. The parameters are used depending on the particular subclass.
     * @param tasks list of tasks to execute on
     * @param ui ui to input/output to
     * @param storage file storage interface
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    protected final void setExit() {
        isExit = true;
    }

    /**
     * Returns whether the program should exit.
     * @return true if the program should exit
     */
    public final boolean isExit() {
        return isExit;
    }
}
