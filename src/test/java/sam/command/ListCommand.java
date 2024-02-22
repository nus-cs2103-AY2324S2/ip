package sam.command;

import sam.Storage;
import sam.TaskList;
import sam.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.displayList();
    }
}