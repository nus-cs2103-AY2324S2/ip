public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        super("delete");
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        Task removedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.taskDeleted(removedTask, tasks);
        storage.overwritePreviousSave(tasks);
    }

}
