package Command;

import Task.TaskList;
import Dook.Ui;
import Dook.Storage;
import Dook.DookException;

public class ByeCommand extends Command {

    public ByeCommand() {
        this.isExit = true;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DookException {
        ui.println("Bye :(. Hope to see you again soon! ≽^- ˕ -^≼");
        storage.write(tasks);
    }
}
