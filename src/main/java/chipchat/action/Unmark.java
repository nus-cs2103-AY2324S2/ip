package chipchat.action;

import chipchat.exception.ChipchatException;
import chipchat.storage.Storage;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

/**
 * Represents an executable action that will perform the unmark operation on a given task list.
 */
public class Unmark extends Action {
    private final String SUCCESS_MSG = "Nice! I've unmarked this task as not done:";
    private final int index;

    /**
     * Initializes the task with the specific index.
     *
     * @param index the index of a task in the list to be unmarked as not done
     */
    public Unmark(int index) {
        this.index = index;
    }

    /**
     * Unmarks the task with pre-specified index from given task list as not done.
     *
     * @param tasks task list to perform operations on
     * @param ui ui of application
     * @param storage storage of application
     */
    public void run(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmark(this.index);
            storage.save(tasks);
            ui.showMsg(SUCCESS_MSG);
            ui.showMsg(tasks.printTask(this.index));
        } catch (ChipchatException exc) {
            ui.showErrMsg(exc);
        }
    }
}
