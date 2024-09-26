package adam.command;

import adam.AdamException;
import adam.Storage;
import adam.task.TaskList;
import adam.ui.Ui;

/**
 * {@inheritDoc}
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Returns a command to exit the program.
     */
    public ExitCommand() {
    }

    /**
     * {@inheritDoc}
     * Exits the program.
     *
     * @param taskList Current TaskList of program.
     * @param ui Ui used by the program.
     * @param storage Storage used by the program.
     * @return The result of the command executed to be printed as the program's response.
     * @throws AdamException If command cannot be executed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AdamException {
        storage.save(taskList);
        return ui.showBye();
    }

    /**
     * {@inheritDoc}
     *
     * @return True if program will exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
