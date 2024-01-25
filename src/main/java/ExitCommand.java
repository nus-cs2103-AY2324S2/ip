public class ExitCommand extends Command {
    ExitCommand() {
        super("");
    }

    @Override
    void execute(TaskList taskList, Ui ui) {
        ui.farewell();
    }

    @Override
    boolean isExit() {
        return true;
    }
}
