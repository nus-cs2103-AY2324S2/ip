package bob;

public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
