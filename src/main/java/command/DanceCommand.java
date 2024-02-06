package command;

import dook.Storage;
import dook.Ui;
import task.TaskList;

public class DanceCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printDance();
        ui.println("Hope you liked my dance!!! meow");
    }
}
