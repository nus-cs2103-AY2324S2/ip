package view;

import model.Task;

/**
 * The {@code UnmarkTaskView} class represents a view for displaying information after marking a task as not done.
 */
public class UnmarkTaskView extends Ui {
    private final Task task;

    /**
     * Constructs a {@code UnmarkTaskView} with the specified task.
     *
     * @param task The task that has been marked as not done.
     */
    public UnmarkTaskView(Task task) {
        this.task = task;
    }

    /**
     * Displays information about the unmarked task, including its details and the updated status.
     */
    @Override
    public void display() {
        System.out.println(
            "____________________________________________________________\n"
            + " OK, I've marked this task as not done yet:\n"
            + "   " + this.task.toString() + "\n"
            + "____________________________________________________________\n"
        );
    }
}
