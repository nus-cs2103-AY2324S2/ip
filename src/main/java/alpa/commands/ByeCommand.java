package alpa.commands;

import alpa.utils.Storage;
import alpa.tasks.TaskList;
import alpa.ui.Ui;

/**
 * Represents a command that displays a goodbye message and exits the program.
 */
public class ByeCommand implements Command {
  
    /**
     * Executes the ByeCommand, which displays a goodbye message and exits the program.
     *
     * @param taskList the TaskList object that stores the tasks
     * @param ui the Ui object that handles user interface interactions
     * @param storage the Storage object that handles data storage
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
        System.exit(0);
    }

    /**
     * Returns whether or not the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }
  
    /**
     * Gets the name of the command.
     *
     * @return the name of the command.
     */
    public String getName() {
        return "Bye";
    }
}
