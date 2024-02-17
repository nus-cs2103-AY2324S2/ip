package view;

import objects.Task;

/**
 * The UnmarkedTask class provides a method to generate a message indicating that a task has been marked as not done.
 */
public class UnmarkedTask {

    /**
     * Generates a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return A message confirming the marking of the task as not done.
     */
    public static String display(Task task) {
        return String.format("Nice! I've marked this task as not done yet:\n   %s", task.toString());
    }
}
