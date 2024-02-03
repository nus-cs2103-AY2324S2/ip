package duke.commands;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     */
    public abstract void execute(TaskList tasks, Ui ui);

    public boolean isExit() {
        return false;
    }
}
