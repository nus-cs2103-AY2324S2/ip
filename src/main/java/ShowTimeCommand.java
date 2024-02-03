public class ShowTimeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCurrentDateTime();
    }
}
