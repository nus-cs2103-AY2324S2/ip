package view;

import model.Task;
import model.TaskList;

/**
 * The {@code AddTaskView} class represents a view for displaying information after adding a {@code Task}.
 */
public class AddTaskView extends Ui {
    private final Task task;
    private final String size;

    /**
     * Constructs an {@code AddTaskView} with the specified {@code Task} and {@code TaskList}.
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
    public void display() {
        System.out.println(
            "____________________________________________________________\n"
            + "   Got it. I've added this task:\n"
            + "   " + this.task.toString() + "\n"
            + "   Now you have " + this.size + " tasks in the list.\n"
            + "____________________________________________________________\n"
        );
    }
}
