package jav.command;

import java.io.IOException;

import jav.exception.InvalidParamException;
import jav.manager.FileManager;
import jav.manager.StorageManager;
import jav.manager.UiManager;
import jav.task.Task;

/**
 * DeleteTaskCommand handles the executing of deletion of tasks.
 */
public class DeleteTaskCommand extends TaskCommand {
    private String deletedTaskParam;
    private boolean deletedTaskMarkedStatus;
    private StorageManager.StorageType deletedTaskType;

    /**
     * Constructs a new DeleteTaskCommand.
     *
     * @param param the parameter of the command.
     * @return a new DeleteTaskCommand.
     */
    public DeleteTaskCommand(String param) {
        this.param = param;
    }

    @Override
    public String execute() throws InvalidParamException, IOException {
        // Check if given an integer
        int index = 0;
        try {
            index = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            throw new InvalidParamException("Cannot delete task, given param is not num", null);
        }

        // Check if given a -ve index
        if (index < 1) {
            throw new InvalidParamException("Cannot delete task, given num is -ve", null);
        }

        Task task = StorageManager.getInstance().getTask(index - 1);
        deletedTaskParam = task.getFileFormatParam();
        deletedTaskType = task.getType();
        deletedTaskMarkedStatus = task.isMarked();

        // Check if given an index bigger than size of list
        if (StorageManager.getInstance().deleteTask(index - 1)) {
            FileManager.getInstance().saveStorageData(StorageManager.getInstance().getFileFormat());

            return UiManager.getInstance().printDeletingTask();
        } else {
            throw new InvalidParamException("Cannot delete task, given num is out of scope", null);
        }
    }

    @Override
    public String undo() throws IOException {
        int index = Integer.parseInt(param);
        StorageManager.getInstance().store(deletedTaskParam, deletedTaskType, deletedTaskMarkedStatus, index - 1);
        FileManager.getInstance().saveStorageData(StorageManager.getInstance().getFileFormat());

        return UiManager.getInstance().printUndo();
    }
}
