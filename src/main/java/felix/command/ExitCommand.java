package felix.command;

import felix.exception.FelixException;
import felix.utils.TaskList;
import felix.utils.Ui;
import felix.utils.Storage;
public class ExitCommand extends Command {
    public ExitCommand() {
        this.isExit = true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        ui.exitProgram();
    }
}
