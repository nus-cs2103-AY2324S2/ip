package commands;

import util.Ui;
import util.TaskList;
import util.Storage;

/**
 * The ByeCommand class represents a command to exit the chatbot application.
 * It extends the Command class and provides the execute method to perform the exit action.
 */
public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand.
     * Prints a goodbye message using the Ui, then exits the application.
     *
     * @param tasks   The TaskList containing the current tasks (not used in this command).
     * @param ui      The Ui instance for user interaction and output.
     * @param storage The Storage instance for saving tasks (not used in this command).
     */
    @Override
    public UserCommand execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMessage();
        return new UserCommand("Exiting the program...");
    }
}
