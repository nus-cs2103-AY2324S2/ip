package command;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;

/**
 * A OtherCommand that prints invalid instruction.
 * A subclass of Command class.
 */
public class OtherCommand extends Command {

    /**
     * Prints a statement of invalid instruction.
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @throws AndeluException
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws AndeluException {
        ui.printInvalidFeature();
    }
}
