package sam.command;

import sam.Storage;
import sam.TaskList;
import sam.Ui;
import sam.SamException;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.displayList();
    }
}