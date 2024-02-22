package alpa.commands;

import alpa.tasks.TaskList;
import alpa.utils.Storage;

/**
 * Represents a command that displays a goodbye message and exits the program.
 */
public class ByeCommand implements Command {

    /**
     * Executes the Bye command, which ends the program.
     *
     * @param taskList the task list to be operated on
     * @param storage the storage to save the task list
     * @return a farewell message
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return "It's been a pleasure grazing through your tasks! Goodbye human! Stay cozy!";
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
}
