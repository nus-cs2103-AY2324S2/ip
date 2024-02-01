public class ExitCommand extends Command {
    ExitCommand() {
        this.isExit = true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        ui.exitProgram();
    }
}
