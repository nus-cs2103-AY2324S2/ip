package tyler.command;

import tyler.storage.Storage;
import tyler.task.TaskList;
import tyler.ui.Ui;

/**
 * Represent a Find Command to find a task that contains a specific keyword.
 * A keyword argument is stored in this Find Command.
 */
public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Execution of Find Command is finding the task from taskList given
     * the keyword. Then, print the tasks that matched the keyword.
     *
     * @param tasks   The object which stored the list of tasks.
     * @param ui      The User Interface of the program.
     * @param storage The storage that can load or save task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.find(this.keyword);
    }
}
