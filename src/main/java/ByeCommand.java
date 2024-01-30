public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}