public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTask(input, ui, storage);
    }
}
