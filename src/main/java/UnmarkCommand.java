public class UnmarkCommand extends Command {
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkTask(input, ui, storage);
    }
}
