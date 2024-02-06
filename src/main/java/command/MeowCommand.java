package command;

import dook.Storage;
import dook.Ui;
import task.TaskList;

public class MeowCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.println("meow!");
    }
}
