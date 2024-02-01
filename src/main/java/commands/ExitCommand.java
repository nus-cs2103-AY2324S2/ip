package commands;

import excceptions.WeiException;
import taskList.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws WeiException {
    }

    @Override
    public boolean isExit() {
        return true;
    }


}
