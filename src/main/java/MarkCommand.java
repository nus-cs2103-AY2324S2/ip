public class MarkCommand extends Command {
    protected int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTask(this.index - 1).mark();
    }
}
