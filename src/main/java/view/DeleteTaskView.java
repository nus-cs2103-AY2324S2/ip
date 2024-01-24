package view;

import model.Task;
import model.TaskList;

/**
 * The {@code DeleteTaskView} class represents a view for displaying information after deleting a {@code Task}.
 */
public class DeleteTaskView extends Ui {
    private final Task task;
    private final String size;

    /**
     * Constructs an {@code DeleteTaskView} with the specified {@code Task} and {@code TaskList}.
     *
     * @param task The task that has been deleted.
     * @param taskList The task list to which the task has been deleted.
     */
    public DeleteTaskView(Task task, TaskList taskList) {
        this.task = task;
        this.size = String.valueOf(taskList.size());
    }

    /**
     * Displays information about the deleted task, including its details and the updated task list size.
     */
    @Override
    public void display() {
        System.out.println(
            "____________________________________________________________\n"
            + "   Noted. I've removed this task:\n"
            + "   " + this.task.toString() + "\n"
            + "   Now you have " + this.size + " tasks in the list.\n"
            + "____________________________________________________________\n"
        );
    }
}
