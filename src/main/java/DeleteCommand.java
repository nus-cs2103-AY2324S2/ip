public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(TaskList tasks, Ui ui, Storage storage, int taskIndex) {
        super(tasks, ui, storage);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws AtlasException {
        ui.showTaskDeleted(tasks, taskIndex);
        tasks.deleteTask(taskIndex);
    }
}
