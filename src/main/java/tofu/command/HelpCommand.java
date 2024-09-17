package tofu.command;

import tofu.TofuException;
import tofu.task.TaskList;
import tofu.ui.Ui;

/**
 * Display all the available commands.
 */
public class HelpCommand implements Command {

    public String execute(TaskList tasks, Ui ui) throws TofuException {
        return Ui.availableCommands();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HelpCommand;
    }
    public boolean isExit() {
        return false;
    }
}
