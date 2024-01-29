public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        Task task = tasks.unmark(index);
        storage.rewriteFile(tasks);
        ui.showUnmark(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
