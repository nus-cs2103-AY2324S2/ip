package chimp.command;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

public class MarkCommand extends Command {
    int num;

    public MarkCommand(int num) {
        this.num = num;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws CommandExecuteException {
        if (num < 1 || num > list.size())
            throw new CommandExecuteException("mark must have number argument");
        list.get(num - 1).mark();
        ui.say("mark", list.get(num - 1), list);
    }

}
