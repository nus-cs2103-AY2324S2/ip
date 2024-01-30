public class InvalidCommand extends Command {
    public InvalidCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
    }

    @Override
    public void execute() {
        ui.showError("Invalid command entered. Please try again.");
    }
}
