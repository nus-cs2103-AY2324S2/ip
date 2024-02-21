package commands;

import util.Ui;
import util.TaskList;
import util.Storage;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command to exit the program.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage component.
     * @return A user command indicating the program is exiting.
     */
    @Override
    public UserCommand execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMessage();
        return new UserCommand("Goodbye! See you again.");
    }
}