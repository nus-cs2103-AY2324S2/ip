package view;

import objects.Task;

/**
 * The MarkedTask class provides a method to generate a message indicating that a task has been marked as done.
 */
public class MarkedTask extends UI {

    /**
     * Generates a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return A message confirming the marking of the task as done.
     */
    public static String display(Task task) {
        return String.format("Good job! I've marked the task as dones:\n   %s", task.toString());
    }
}
