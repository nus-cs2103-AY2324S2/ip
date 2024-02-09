package duke.command;

import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * Class that represents a Command that Lists {@link duke.task.Task} in the {@link TaskList}.
 */
public class ListTasksCommand extends Command {
    /**
     * Runs the ListTasksCommand to print out the list of Tasks currently in the TaskList.
     *
     * @param taskList Existing TaskList to be updated.
     * @param ui Existing UserInterface Object.
     * @param storage Existing Storage to be updated
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showTaskListContents(taskList.getTaskStore());
    }

    /**
     * Returns a boolean value telling us whether this command is an exit command.
     *
     * @return Boolean value indicating whether this command is an exist command.
     */
    public boolean isExit() {
        return false;
    }
}
