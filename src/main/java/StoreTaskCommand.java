import java.io.IOException;

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
     * 
     * @return a new StoreTaskCommand.
     */
    public StoreTaskCommand(StorageManager.StorageType storageType, String param) {
        type = storageType;
        this.param = param;
    }

    @Override
    public void Execute() throws IOException, InvalidParamException {
        try {
            StorageManager.getInstance().store(param, type);
            FileManager.getInstance().saveStorageData(StorageManager.getInstance().getFileFormat());
            UiManager.getInstance().printStoring();
        } catch (IOException ioe) {
            throw ioe;
        } catch (InvalidParamException ipe) {
            throw ipe;
        }
    }
}
