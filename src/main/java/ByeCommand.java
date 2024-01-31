public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        ui.showBye();
    }
}
