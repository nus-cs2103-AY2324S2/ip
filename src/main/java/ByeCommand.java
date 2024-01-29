public class ByeCommand extends Command {


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayToScreen("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
