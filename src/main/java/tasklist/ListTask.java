package tasklist;

import java.util.List;

import tasks.Task;
import ui.Ui;

/**
 * To list all the task
 */
public class ListTask {
    /**
     * No constructor needed
     */
    private ListTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * List out all the tasks
     * @param taskList List of tasks
     */
    public static String execListTask(List<List<Task>> taskList) {
        StringBuilder response = new StringBuilder();
        Ui.listingStart(response);
        if (taskList.isEmpty()) {
            Ui.emptyResult(response);
        } else {
            Ui.listTasks(taskList, response);
        }
        return response.toString();
    }

}
