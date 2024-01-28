import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws IOException, CommandException {
        ui.showFarewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
