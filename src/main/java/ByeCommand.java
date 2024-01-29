public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendGoodbyeMessage();
        System.exit(0);
    }
}
