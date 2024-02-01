package Duke;

// Commands.ExitCommand class
public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}
