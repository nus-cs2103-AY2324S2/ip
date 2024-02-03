public class ExitCommand extends Command {

    public ExitCommand() {
        super("exit");
        super.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        ui.closeScanner();
    }
}
