package chipchat.action;

import chipchat.exception.ChipchatException;
import chipchat.storage.Storage;
import chipchat.task.Task;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

/**
 * Represents an executable action that will delete a task from the given task list.
 */
public class Delete extends Action {
    private final String SUCCESS_MSG = "Noted. I've removed this task:";
    private final int index;

    /**
     * Initializes the task with the specific index.
     *
     * @param index the index of a task in the list to be deleted
     */
    public Delete(int index) {
        this.index = index;
    }

    /**
     * Deletes the task with pre-specified index from given task list.
     *
     * @param tasks task list to perform operations on
     * @param ui ui of application
     * @param storage storage of application
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removedTask = tasks.delete(this.index);
            storage.save(tasks);
            ui.showMsg(SUCCESS_MSG);
            ui.showMsg(removedTask.toString());
        } catch (ChipchatException exc) {
            ui.showErrMsg(exc);
        }
    }
}
