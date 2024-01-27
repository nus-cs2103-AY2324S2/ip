package duke.command;

import duke.common.TaskList;
import duke.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.List;

/**
 * Represents a list command initiated by the user. <code>ListCommand</code> would list all tasks that the users have.
 */
public class ListCommand  extends Command{
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_USAGE = COMMAND_WORD + ":" + "list all tasks.\n" + "Example: " + COMMAND_WORD;

    /**
     * Shows the tasks that the users have
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }


}
