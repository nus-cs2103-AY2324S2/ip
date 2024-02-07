package chimp.command;

import chimp.task.Task;
import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws CommandExecuteException{
        if (index - 1 < 0 || index - 1 >= list.size())
            throw new CommandExecuteException("Invalid indexber argument provided to delete");
        Task task = list.get(index - 1);
        list.remove(index - 1);
        ui.say("delete", task, list);
    }

}
