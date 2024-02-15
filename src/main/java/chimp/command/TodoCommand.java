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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        tasks.add(text);
        return ui.say(tasks.get(tasks.size() - 1), tasks);
    }

}
