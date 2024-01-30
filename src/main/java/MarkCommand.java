public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(TaskList tasks, Ui ui, Storage storage, int taskIndex) {
        super(tasks, ui, storage);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws AtlasException {
        tasks.markTask(taskIndex);
        ui.showMark(super.tasks, taskIndex);
    }
}
