package commands;

import helpers.TaskList;
import helpers.Ui;

/**
 * The ExitCommand class represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param ui       The user interface object.
     * @param taskList The task list object.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.displayGoodByeMessage(); // Display farewell message to the user
        this.setIsExit(); // Set this command as an exit command
    }
}
