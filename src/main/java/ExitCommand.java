public class ExitCommand extends Command {

    public boolean isExit;

    public ExitCommand() {
        super("exit");
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
