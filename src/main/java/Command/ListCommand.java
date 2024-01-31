package Command;

import Task.TaskList;
import Dook.Ui;
import Dook.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.println("Here are your tasks!");
        ui.println(tasks.toString());
    }
}
