package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A ExitCommand to exit the program.
 * A subclass of Command class.
 */
public class ExitCommand extends Command {

    /**
     * Exits the program.
     *
     * @param tasks the Task Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @throws DukeException
     */
    @Override
    public void excuteCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.confirmExit();
        ui.printByeStatement();
    }
}
