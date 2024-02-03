package raphael.command;

import raphael.exception.RaphaelException;
import raphael.task.TaskList;
import raphael.ui.Ui;
import raphael.storage.Storage;

/**
 * Modifies the isDone property of a task indicated by index
 */
public class EditCommand extends Command {
    private final int idx;
    private final boolean isMark;
    public EditCommand(int idx, boolean isMark) {
        this.idx = idx;
        this.isMark = isMark;
    }

    /**
     * Executes the current command. Upon execution, the task indicated by instance field will be modified. If the
     * isCheck boolean is true, then the task is marked as done, marked as undone otherwise.
     * The Ui object will print the relevant output onto the terminal. The file used to store the tasks
     * will be updated by the Storage object.
     *
     * @param tasks the task list
     * @param ui the user interface object
     * @param storage the file I/O object
     * @throws raphael.exception.RaphaelException exception exclusive to raphael
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RaphaelException {
        if (this.isMark) {
            ui.showMarkOutput(tasks.checkTask(this.idx));
        } else {
            ui.showUnmarkOutput(tasks.uncheckTask(this.idx));
        }
        storage.write(tasks.toFileFormat());
    }
}
