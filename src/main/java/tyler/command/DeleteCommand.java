package tyler.command;

import tyler.ui.Ui;
import tyler.storage.Storage;
import tyler.task.Task;
import tyler.task.TaskList;

/**
 * Represent a delete command. A Delete Command has an index which is the index of
 * the taskList that needed to be deleted.
 */
public class DeleteCommand extends Command {
    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execution of Delete command delete the index of the taskList. At last, this
     * execution end with an Ui, showed the line that the task is deleted.
     *
     * @param tasks   The object which stored the list of tasks.
     * @param ui      The User Interface of the program.
     * @param storage The storage that can load or save task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.getTask(this.index - 1);
        tasks.deleteTask(this.index - 1);
        int num = tasks.getNumOfTasks();
        ui.showTaskDeleted(deletedTask, num);
    }
}
