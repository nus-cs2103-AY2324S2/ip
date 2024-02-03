public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showHelpMsg();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
