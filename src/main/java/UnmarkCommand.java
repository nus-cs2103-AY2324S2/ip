public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(TaskList tasks, Ui ui, Storage storage, int taskIndex) {
        super(tasks, ui, storage);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws AtlasException {
        tasks.unmarkTask(taskIndex);
        ui.showunMark(tasks, taskIndex);
    }
}
