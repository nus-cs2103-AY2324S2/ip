package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;

/**
 * Represents a command to termination of the application.
 * When executed, this command shows a farewell message to the user, saves the current state of the
 * task list to storage, and signals the application to terminate.
 */
public class ByeCommand extends Command{

    /**
     * Executes the command to terminate the application. It displays a farewell message, saves the tasks to storage,
     * and indicates that the application should stop running.
     * @param ui The user interface to interact with.
     * @param taskList The task list to be manipulated or queried.
     * @param storage The storage system for loading or saving tasks.
     * @return true to indicate the application should stop running.
     */
    @Override
    public boolean handle(Ui ui, TaskList taskList, Storage storage){
        ui.showFarewell();
        storage.writeTasks(taskList);
        return true;
    }
}
