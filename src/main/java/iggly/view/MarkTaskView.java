package iggly.view;

import iggly.model.Task;

/**
 * The {@link MarkTaskView} class represents a view for displaying information after marking a task as done.
 */
public class MarkTaskView extends Ui {
    private final Task task;

    /**
     * Constructs a {@link MarkTaskView} with the specified task.
     *
     * @param task The task that has been marked as done.
     */
    public MarkTaskView(Task task) {
        this.task = task;
    }

    /**
     * Displays information about the marked task, including its details and the updated status.
     */
    @Override
    public String display() {
        return (
            "\uD83D\uDC27 Nice! I've marked this task as done:\n"
            + "   " + this.task.toString() + "\n");
    }
}
