package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;

/**
 * Represents a command to terminate the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command to terminate the application. Saves the current state of the task list to storage
     * @param ui The user interface to interact with.
     * @param taskList The task list to be manipulated or queried.
     * @param storage The storage system for loading or saving tasks.
     * @return String message to be displayed to the user.
     */
    @Override
    public String handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        storage.writeTasks(taskList);
        return ui.showFarewell();
    }

    /**
     * Indicates whether the command is an exit command.
     * @return True if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
