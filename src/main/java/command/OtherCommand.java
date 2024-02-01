package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * @throws DukeException
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printInvalidFeature();
    }
}
