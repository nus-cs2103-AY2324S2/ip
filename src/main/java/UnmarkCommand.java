public class UnmarkCommand extends Command{
    protected int index;

    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws OrkException {
        try {
            Task task = TaskList.list.get(index);
            if (index + 1 > TaskList.list.size()) {
                throw new OrkException("No such task");
            }
            task.undone();
            Ui.printDoneMessage(task);
            Storage.writeToDisk(tasks);
        } catch (OrkException exception) {
            ui.printException(exception);
        }
    }
}
