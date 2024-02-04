public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.displayGoodByeMessage();
        this.setIsExit();
    }
}
