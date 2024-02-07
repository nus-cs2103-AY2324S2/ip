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
    public void execute(TaskList list, Ui ui, Storage storage) throws CommandExecuteException {
        if (num < 1 || num > list.size())
            throw new CommandExecuteException("unmark must have number argument");
        list.get(num - 1).unmark();
        ui.say("unmark", list.get(num - 1), list);
    }

}
