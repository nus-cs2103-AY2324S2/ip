package felix.command;

import felix.exception.FelixException;
import felix.utils.TaskList;
import felix.utils.Ui;
import felix.utils.Storage;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        ui.println(tasks);
    }
}
