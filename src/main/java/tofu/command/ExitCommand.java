package tofu.command;

import tofu.TofuException;
import tofu.task.TaskList;
import tofu.ui.Ui;

public class ExitCommand implements Command {

    public String execute(TaskList tasks, Ui ui) throws TofuException {
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }

    public boolean isExit() {
        return true;
    }
}
