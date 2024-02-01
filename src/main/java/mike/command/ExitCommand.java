package mike.command;

import mike.TaskList;
import mike.Ui;

/**
 * Exits the program.
 * @author ningc
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList) {
        Ui.farewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

