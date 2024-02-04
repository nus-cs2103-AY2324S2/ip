public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws ParameterException {
        ui.print(tasks.unmark(this.idx));
    }
}
