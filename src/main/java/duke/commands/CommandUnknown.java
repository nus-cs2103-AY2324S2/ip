package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the command for handling unknown or invalid commands in the Duke application.
 */
public class CommandUnknown extends Command {
    /**
     * Constructs a new CommandUnknown object.
     */
    public CommandUnknown() {}

    /**
     * Executes the command to handle unknown or invalid commands and displays an error message to the user.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface component for displaying error messages to the user.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("OOPS!!! I don't understand that command, try again later.");
    }
}
