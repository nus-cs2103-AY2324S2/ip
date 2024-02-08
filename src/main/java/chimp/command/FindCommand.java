package chimp.command;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;
import chimp.task.Task;

public class FindCommand extends Command{
    String arg;

    public FindCommand(String arg) {
        this.arg = arg;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        Task[] matches = tasks.find(arg);
        ui.say("find", matches, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}
