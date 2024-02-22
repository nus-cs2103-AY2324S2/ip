package chatteroo.commands;

import chatteroo.tasks.TaskList;
import chatteroo.ui.Ui;
import chatteroo.storage.Storage;

/**
 * Prints the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructor for the ListCommand class.
     */
    public ListCommand() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int listCount = tasks.getTaskListSize();
        if (listCount == 0) {
            return ui.showListTaskResponse(listCount);
        }
        return ui.showListTaskResponse(listCount) + tasks.getTaskListString();
    }
}
