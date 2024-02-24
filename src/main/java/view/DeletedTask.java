package view;

import objects.Task;
import objects.TaskList;

/**
 * The DeletedTask class provides a method to generate a message confirming the deletion
 * of a task from the task list.
 */
public class DeletedTask extends UI {

    /**
     * Generates a message confirming the deletion of a task from the task list.
     *
     * @param tasks The task list from which the task was deleted.
     * @param task  The task that was deleted.
     * @return A confirmation message indicating the deleted task and the updated
     * number of tasks in the list.
     */
    public static String display(TaskList tasks, Task task) {
        return String.format("I've removed this task:\n %s\nYou now have %d tasks left.",
                task.toString(), tasks.size());
    }
}
