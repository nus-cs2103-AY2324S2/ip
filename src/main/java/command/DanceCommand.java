package command;

import dook.Storage;
import dook.Ui;
import task.TaskList;

public class DanceCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printDance();
        return "Hope you liked my dance!!! meow";
    }
}
