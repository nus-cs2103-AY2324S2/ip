package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;


/**
 * This command handles the listing of tasks in the existing task list.
 */

public class ListCommand extends Command {

    /**
     * Constructs a ListCommand with the specified TaskList, Ui, and Storage.
     * This command is responsible for displaying all tasks in the task list.
     *
     * @param tasks   The TaskList containing the tasks to be listed.
     * @param ui      The Ui instance for interacting with the user.
     * @param storage The Storage instance, not directly used by this command.
     */
    public ListCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
    }

    /**
     * Executes the listing of all tasks in the task list. The tasks are displayed
     * to the user through the Ui.
     */
    @Override
    public String execute() {
        return ui.showAllTasks(tasks.getTasks());
    }
}
