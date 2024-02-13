package tasklist;

import java.util.List;

import tasks.Task;

/**
 * To list all the task
 */
public class ListTask {
    /**
     * No constructor needed
     */
    private ListTask() {
        throw new AssertionError("Constructor is not allowed");
        // assert false : "Execution should never reach this point!";
    }

    /**
     * List out all the tasks
     * @param taskList List of tasks
     */
    public static String execListTask(List<Task> taskList) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list:\n");
        int pos = 1;
        if (taskList.isEmpty()) {
            response.append("List is empty.");
        } else {
            for (Task acting : taskList) {
                response.append(pos++).append(". ").append(acting).append("\n");
            }
        }
        return response.toString();
    }

}
