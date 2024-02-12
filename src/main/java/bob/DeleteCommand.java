package bob;

public class DeleteCommand extends Command {
    int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws InvalidTaskIndexException, SavingException {
        Task task = taskList.delete(taskIndex);
        ui.showDelete(task, taskList.getSize());
        taskList.updateStorage(storage);
    }
}
