package tyler.command;

import tyler.storage.Storage;
import tyler.task.TaskList;
import tyler.ui.Ui;

/**
 * Represents a List Command.
 */
public class ListCommand extends Command {

    /**
     * Execution of List Command allows user to print out the taskList currently.
     *
     * @param tasks   The object which stored the list of tasks.
     * @param ui      The User Interface of the program.
     * @param storage The storage that can load or save task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.list(tasks);
    }
}
