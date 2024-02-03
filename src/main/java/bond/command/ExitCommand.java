package bond.command;

import bond.main.Storage;
import bond.main.Ui;
import bond.task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("bye");
        super.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        ui.closeScanner();
    }
}
