package henry.command;

import henry.Storage;
import henry.TaskList;
import henry.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}
