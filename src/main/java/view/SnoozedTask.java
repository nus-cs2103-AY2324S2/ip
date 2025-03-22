package view;

import objects.Task;

/**
 * The SnoozedTask class represents a view component for displaying a message when a task is snoozed.
 * Extends UI.
 */
public class SnoozedTask extends UI {

    /**
     * Displays a message indicating that a task has been snoozed.
     *
     * @param task The task that has been snoozed.
     * @return A string message indicating that the task has been snoozed.
     */
    public static String display(Task task) {
        return String.format("I've delayed this task by 1 hour:\n   %s", task.toString());
    }
}
