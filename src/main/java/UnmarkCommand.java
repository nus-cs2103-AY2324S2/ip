public class UnmarkCommand extends Command {
    protected int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTask(this.index - 1).unmark();
    }
}
