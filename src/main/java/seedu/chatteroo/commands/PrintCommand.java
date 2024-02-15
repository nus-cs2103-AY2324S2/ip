package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

/**
 * Prints the list of tasks.
 */
public class PrintCommand extends Command {

    /**
     * Constructor for the PrintCommand class.
     */
    public PrintCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int listCount = tasks.getTaskListSize();
        if (listCount == 0) {
            return ui.showListTaskText(listCount);
        }
        return ui.showListTaskText(listCount) + tasks.getTaskListString();
    }
}
