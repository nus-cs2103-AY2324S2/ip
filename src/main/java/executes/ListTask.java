package executes;

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
    }

    /**
     * List out all the tasks
     * @param taskList
     */
    public static void execListTask(List<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        int pos = 1;
        if (taskList.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            for (Task acting : taskList) {
                System.out.println(pos++ + ". " + acting);
            }
        }
    }

}
