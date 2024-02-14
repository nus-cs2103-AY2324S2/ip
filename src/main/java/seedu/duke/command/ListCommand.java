package seedu.duke.command;

import seedu.duke.common.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;


/**
 * Represents a list command initiated by the user. <code>ListCommand</code> would list all tasks that the users have.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_USAGE = COMMAND_WORD + ":" + "list all tasks.\n" + "Example: " + COMMAND_WORD;

    /**
     * Shows the tasks that the users have
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.generateTaskList(taskList);
    }


}
