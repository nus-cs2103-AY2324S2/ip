package iggly.view;

import iggly.model.Task;
import iggly.model.TaskList;

/**
 * The {@link AddTaskView} class represents a view for displaying information after adding a {@link Task}.
 */
public class AddTaskView extends Ui {
    private final Task task;
    private final String size;

    /**
     * Constructs an {@link AddTaskView} with the specified {@link Task} and {@link TaskList}.
     *
     * @param task The task that has been added.
     * @param taskList The task list to which the task has been added.
     */
    public AddTaskView(Task task, TaskList taskList) {
        this.task = task;
        this.size = String.valueOf(taskList.size());
    }

    /**
     * Displays information about the added task, including its details and the updated task list size.
     */
    @Override
    public String display() {
        return (
            "   Got it! I've added this task:\n"
            + "   " + this.task.toString() + "\n"
            + "   Now you have " + this.size + " tasks in the list. \uD83D\uDC27");
    }
}
