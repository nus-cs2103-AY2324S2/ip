package view;

import model.Task;
import model.TaskList;

public class AddTaskView extends Ui {
    private final Task task;
    private final String size;
    public AddTaskView(Task task, TaskList taskList) {
        this.task = task;
        this.size = String.valueOf(taskList.size());
    }

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
