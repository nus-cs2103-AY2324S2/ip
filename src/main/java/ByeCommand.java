public class ByeCommand extends Command{
    public ByeCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();;
        ui.closeScanner();
    }

    public boolean isWorking() {
        return false;
    }
}
