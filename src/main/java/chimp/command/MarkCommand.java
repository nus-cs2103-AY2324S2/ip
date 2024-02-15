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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        if (num < 1 || num > tasks.size()) {
            throw new CommandExecuteException("mark must have number argument");
        }
        tasks.get(num - 1).mark();
        return ui.say("mark", tasks.get(num - 1), tasks);
    }

}
