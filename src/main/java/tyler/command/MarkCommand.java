package tyler.command;
import tyler.task.TaskList;
import tyler.storage.Storage;
import tyler.ui.Ui;

/**
 * Represents a Mark Command. The Mark Command has an extra argument which is index.
 * The index is needed to determine which task is needed to be marked.
 */
public class MarkCommand extends Command {
    protected int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Execution of Mark Command is getting the task from taskList given the index.
     * Then, mark the specific task to is done.
     *
     * @param tasks   The object which stored the list of tasks.
     * @param ui      The User Interface of the program.
     * @param storage The storage that can load or save task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTask(this.index - 1).mark();
    }
}
