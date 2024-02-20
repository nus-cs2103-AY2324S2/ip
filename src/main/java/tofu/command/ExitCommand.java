package tofu.command;

import tofu.TofuException;
import tofu.task.TaskList;
import tofu.ui.Ui;

public class ExitCommand implements Command {

    public String execute(TaskList tasks, Ui ui) throws TofuException {
        return "";
    }

    public boolean isExit() {
        return true;
    }
}
