package jav.command;

import java.io.IOException;

import jav.exception.InvalidParamException;
import jav.manager.FileManager;
import jav.manager.StorageManager;
import jav.manager.UiManager;

/**
 * StoreTaskCommand handles the executing of storing of tasks.
 */
public class StoreTaskCommand extends TaskCommand {
    /** The type of task to store. */
    private StorageManager.StorageType type;

    /**
     * Constructs a new StoreTaskCommand.
     *
     * @param storageType the storage type.
     * @param param the parameter of the command.
     * @return a new StoreTaskCommand.
     */
    public StoreTaskCommand(StorageManager.StorageType storageType, String param) {
        type = storageType;
        this.param = param;
    }

    @Override
    public String execute() throws IOException, InvalidParamException {
        try {
            StorageManager.getInstance().store(param, type);
            FileManager.getInstance().saveStorageData(StorageManager.getInstance().getFileFormat());
            return UiManager.getInstance().printStoring();
        } catch (IOException ioe) {
            throw ioe;
        } catch (InvalidParamException ipe) {
            throw ipe;
        }
    }

    @Override
    public String undo() throws Exception {
        StorageManager.getInstance().deleteLatestTask();
        FileManager.getInstance().saveStorageData(StorageManager.getInstance().getFileFormat());

        return UiManager.getInstance().printUndo();
    }
}
