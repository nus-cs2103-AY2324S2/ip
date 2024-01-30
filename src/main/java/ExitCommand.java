public class ExitCommand extends Command {

    @Override
    public void excuteCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.confirmExit();
        ui.printByeStatement();
    }
}
