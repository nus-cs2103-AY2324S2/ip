package chimp.command;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

public class TodoCommand extends Command {
    String text;

    public TodoCommand(String text) {
        this.text = text;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws CommandExecuteException {
        list.add(text);
        ui.say(list.get(list.size() - 1), list);
    }

}
