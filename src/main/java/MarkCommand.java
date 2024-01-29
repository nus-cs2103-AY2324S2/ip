public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        Task task = tasks.mark(index);
        storage.rewriteFile(tasks);
        ui.showMark(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
