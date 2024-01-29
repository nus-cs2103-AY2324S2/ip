public class ExitCommand implements Command {
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
