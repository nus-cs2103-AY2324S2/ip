package chimp.command;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

public class UnmarkCommand extends Command {
    int num;

    public UnmarkCommand(int num) {
        this.num = num;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        if (num < 1 || num > tasks.size()) {
            throw new CommandExecuteException("unmark must have number argument");
        }
        tasks.get(num - 1).unmark();
        return ui.say("unmark", tasks.get(num - 1), tasks);
    }

}
