package chimp.command;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;

public class ExitCommand extends Command{

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.say("bye");
    }

    @Override
    public boolean isExit() {
        return true;
    }
    
}
