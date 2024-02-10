package commands;
import tasks.TaskList;
import ui.Ui;
public class exitCommand extends Command{

    public exitCommand() {

    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        ui.showGoodbye();
        return true;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
