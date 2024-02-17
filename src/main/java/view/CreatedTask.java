package view;

import objects.Task;
import objects.TaskList;

/**
 * The CreatedTask class provides a method to display information about a newly created task.
 */
public class CreatedTask extends UI {

    /**
     * Displays information about a newly created task, including the task details and the total number of tasks.
     *
     * @param tasks The TaskList containing the tasks.
     * @param task  The newly created task.
     * @return
     */
    public static String display(TaskList tasks, Task task) {
        String output = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                task, tasks.size());
        return output;
    }
}
