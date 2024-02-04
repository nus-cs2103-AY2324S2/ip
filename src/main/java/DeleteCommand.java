public class DeleteCommand extends Command {
    private final int i;

    DeleteCommand(int i) {
        this.i = i;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        Task deletedTask = taskList.deleteTask(i - 1);
        ui.displayDeletedTask(deletedTask, taskList.getLength());
    }
}
