package nollid.commands;

import nollid.Storage;
import nollid.TaskList;
import nollid.Ui;

/**
 * ByeCommand class represents a command for exiting the application.
 * It extends the Command class and implements the execute method to perform the exit logic.
 */
public class ByeCommand extends Command {
    /**
     * Overrides the execute method from the Command class.
     * Executes the command to exit the application.
     *
     * @param tasks   The TaskList containing tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for data storage operations.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // TODO: Make this quit the app.
        System.exit(0);
        return "what";
    }
}
