package adam.command;

import adam.AdamException;
import adam.Storage;
import adam.task.TaskList;
import adam.ui.Ui;

/**
 * Represents a command to be executed by the program.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList Current TaskList of program.
     * @param ui Ui used by the program.
     * @param storage Storage used by the program.
     * @return The result of the command executed to be printed as the program's response.
     * @throws AdamException If command cannot be executed.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws AdamException;

    /**
     * Returns true if the command executed exits the program.
     *
     * @return True if program will exit.
     */
    public abstract boolean isExit();
}
