import java.io.IOException;

public class DeleteTaskCommand extends Command{
    private int indexToBeDeleted;

    public DeleteTaskCommand(int index) {
        this.indexToBeDeleted = index;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task deletedTask = taskList.deleteTask(indexToBeDeleted);
        ui.showDeletedTask(deletedTask, taskList.listSize());
        try {
            storage.saveStorage(taskList.getTaskStore());
        } catch(IOException e) {
            ui.showError(e.getMessage());
        }

    }
    public boolean isExit() {
        return false;
    }
}
