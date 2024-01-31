package dibo;
public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.showDeleted(taskList.deleteTask(taskId), taskList.getSize());
        storage.save(taskList);
    }

}
