package tyler.command;

import tyler.storage.Storage;
import tyler.task.TaskList;
import tyler.ui.Ui;

/**
 * Represents an unmarked Command. The Unmarked Command has an extra argument which is
 * index. The index is needed to determine which task is to be unmarked.
 */
public class UnmarkCommand extends Command {
    protected int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Execution of Unmarked Command is getting the task from taskList given the index.
     * Then, unmarked the specific task to be not done.
     *
     * @param tasks   The object which stored the list of tasks.
     * @param ui      The User Interface of the program.
     * @param storage The storage that can load or save task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTask(this.index - 1).unmark();
    }
}
