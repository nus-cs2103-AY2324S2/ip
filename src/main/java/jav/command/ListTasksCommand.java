package jav.command;

import jav.exception.InvalidParamException;
import jav.manager.StorageManager;
import jav.manager.UiManager;

/**
 * ListTasksCommand handles the executing of listing out all stored tasks.
 */
public class ListTasksCommand extends TaskCommand {
    @Override
    public void execute() throws InvalidParamException {
        UiManager.getInstance().printStorage(StorageManager.getInstance().printStoredTasks());
    }
}
