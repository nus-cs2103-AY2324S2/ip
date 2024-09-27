package alastor.command;

import alastor.Storage;
import alastor.TaskList;
import alastor.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
