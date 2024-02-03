public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        super("unmark");
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        if (this.index >= tasks.numberOfTasks()) {
            BondException.raiseException("delete", "INVALID_INDEX");
        }
        Task markedTask = tasks.getTask(index);
        markedTask.markAsIncomplete();
        ui.taskUnmarked(markedTask, tasks);
        storage.overwritePreviousSave(tasks);
    }

}
