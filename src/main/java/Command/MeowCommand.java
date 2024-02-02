package Command;

import Dook.Storage;
import Dook.Ui;
import Task.TaskList;

public class MeowCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.println("meow!");
    }
}
