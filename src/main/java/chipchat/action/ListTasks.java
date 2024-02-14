package chipchat.action;

import chipchat.exception.ChipchatException;
import chipchat.storage.Storage;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

/**
 * Represents an executable task that will print all tasks in a given task list.
 */
public class ListTasks extends Action {
    private final String SUCCESS_MSG = "Here are the tasks in your list:";

    /**
     * Simple constructor.
     */
    public ListTasks() {
    }

    /**
     * Lists and prints all tasks from a given task list.
     *
     * @param tasks task list to perform operations on
     * @param ui ui of application
     * @param storage storage of application
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.showMsg(SUCCESS_MSG);
            ui.showMsg(tasks.toString());
        } catch (ChipchatException exc) {
            ui.showErrMsg(exc);
        }
    }
}
