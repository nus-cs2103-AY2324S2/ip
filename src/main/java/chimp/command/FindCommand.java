package chimp.command;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

public class FindCommand extends Command{
    String arg;

    public FindCommand(String arg) {
        this.arg = arg;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        String[] matches = tasks.find(arg);
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}
