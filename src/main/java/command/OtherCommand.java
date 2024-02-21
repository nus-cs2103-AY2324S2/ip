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
     * Returns a statement of invalid instruction.
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @return The response from Andelu.
     * @throws AndeluException
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws AndeluException {
        return "I'm sorry, I do not understand that.\n";
    }
}
