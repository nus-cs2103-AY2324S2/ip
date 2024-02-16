package reacher.command;

import reacher.Storage;
import reacher.TaskList;
import reacher.Ui;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getTasks());
    }
}
