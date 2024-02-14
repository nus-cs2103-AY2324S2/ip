package chipchat.action;

import java.util.List;

import chipchat.exception.ChipchatException;
import chipchat.storage.Storage;
import chipchat.task.Task;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

/**
 * Represents an executable action that will find a matching task from a given task list
 */
public class Find extends Action {
    private final String SUCCESS_MSG = "Here are possible tasks that match your query:";
    private final String query;

    /**
     * Initializes the action with the query string
     * @param query string to be queried based on
     */
    public Find(String query) {
        this.query = query;
    }

    /**
     * Finds the task based on a query from a given task list.
     *
     * @param tasks task list to perform operations on
     * @param ui ui of application
     * @param storage storage of application
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        try {
            List<Task> matches = tasks.queryByString(this.query);
            StringBuilder taskList = new StringBuilder(SUCCESS_MSG + "\n");
            for (int i = 0; i < matches.size(); i++) {
                taskList.append(String.format("%d. %s\n", i, matches.get(i)));
            }
            ui.showMsg(taskList.toString());
        } catch (ChipchatException exc) {
            ui.showErrMsg(exc);
        }
    }
}
