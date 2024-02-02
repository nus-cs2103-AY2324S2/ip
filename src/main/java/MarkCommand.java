public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        super("mark");
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        Task markedTask = tasks.getTask(index);
        markedTask.markAsComplete();
        ui.taskMarked(markedTask, tasks);
        storage.overwritePreviousSave(tasks);
    }

}
