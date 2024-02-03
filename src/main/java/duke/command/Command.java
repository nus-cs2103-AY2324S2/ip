package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to be executed by the program.
 */
abstract public class Command {
    /**
     * Executes the command.
     *
     * @param tasks Current TaskList of program.
     * @param ui Ui used by the program.
     * @param storage Storage used by the program.
     * @throws DukeException If command cannot be executed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the command executed exits the program.
     *
     * @return True if program will exit.
     */
    public abstract boolean isExit();
}
