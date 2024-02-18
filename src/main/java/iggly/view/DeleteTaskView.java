package iggly.view;

import iggly.model.Task;
import iggly.model.TaskList;

/**
 * The {@link DeleteTaskView} class represents a view for displaying information after deleting a {@link Task}.
 */
public class DeleteTaskView extends Ui {
    private final Task task;
    private final String size;

    /**
     * Constructs an {@link DeleteTaskView} with the specified {@link Task} and {@link TaskList}.
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
    public String display() {
        return (
            "Noted. I've removed this task:\n"
            + "   " + this.task.toString() + "\n"
            + "   Now you have " + this.size + " tasks in the list. \uD83D\uDC27");
    }
}
