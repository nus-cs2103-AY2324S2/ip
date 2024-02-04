package ada.command;

import ada.AdaException;
import ada.Storage;
import ada.task.TaskList;
import ada.ui.Ui;

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
     * @throws AdaException If command cannot be executed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AdaException;

    /**
     * Returns true if the command executed exits the program.
     *
     * @return True if program will exit.
     */
    public abstract boolean isExit();
}
