package command;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;

/**
 * A ExitCommand to exit the program.
 * A subclass of Command class.
 */
public class ExitCommand extends Command {

    /**
     * Exits the program.
     * Returns a bye statement.
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @return The response from Andelu.
     * @throws AndeluException No Exception.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws AndeluException {
        super.confirmExit();

        ui.printByeStatement();
        return "Bye. Hope to see you again soon!";
    }
}
