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
    public static String execListTask(List<List<Task>> taskList) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list:\n");
        int pos = 1;
        if (taskList.isEmpty()) {
            response.append("List is empty.");
        } else {
            int deadlineListIdx = 0;
            List<Task> deadlineList = taskList.get(deadlineListIdx);
            response.append("Deadline: ").append("\n");
            for (Task task : deadlineList) {
                response.append(pos++).append(". ").append(task).append("\n");
            }
            response.append("___________________________").append("\n");

            int eventListIdx = 1;
            List<Task> eventList = taskList.get(eventListIdx);
            response.append("Events: ").append("\n");
            for (Task task : eventList) {
                response.append(pos++).append(". ").append(task).append("\n");
            }
            response.append("___________________________").append("\n");

            int todoListIdx = 2;
            List<Task> todoList = taskList.get(todoListIdx);
            response.append("Todo: ").append("\n");
            for (Task task : todoList) {
                response.append(pos++).append(". ").append(task).append("\n");
            }
            response.append("___________________________").append("\n");

        }
        return response.toString();
    }

}
