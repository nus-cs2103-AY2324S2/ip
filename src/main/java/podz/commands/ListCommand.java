package podz.commands;

import podz.ui.Ui;

/**
 * Represents a command to list all tasks in the task manager.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command to display all tasks to the user.
     *
     * @param ui the user interface that interacts with the user
     */
    @Override
    public void execute(Ui ui) {
        ui.printToUser(super.taskList.toString());
    }
}
