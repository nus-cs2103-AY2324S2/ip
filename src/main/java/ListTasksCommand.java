/**
 * ListTasksCommand handles the executing of listing out all stored tasks.
 */
public class ListTasksCommand extends TaskCommand { 
    @Override
    public void Execute() throws InvalidParamException {
        UiManager.getInstance().printStorage(StorageManager.getInstance().printStoredTasks());
    }
}