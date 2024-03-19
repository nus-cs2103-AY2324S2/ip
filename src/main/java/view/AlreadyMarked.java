package view;

import objects.Task;

/**
 * The AlreadyMarked class provides a method to generate a message indicating that a task has already been marked as
 * done.
 */
public class AlreadyMarked extends UI {

    /**
     * Generates a message indicating that a task has already been marked as done.
     *
     * @param task The task that you are trying to mark.
     * @return A message showing the task is already marked as done.
     */
    public static String display(Task task) {
        return String.format("The task is already done!\n   %s", task.toString());
    }
}
