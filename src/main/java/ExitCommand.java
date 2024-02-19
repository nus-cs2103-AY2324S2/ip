public class ExitCommand extends Command {
    ExitCommand(String command) {
        super(command);
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    boolean isExit() {
        return true;
    }
}

