/*
 * The ByeCommand class is a subclass of Command and represents a command to exit the program.
 * It does not take in any parameters.
 */
public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
