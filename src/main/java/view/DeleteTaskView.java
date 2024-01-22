package view;

import model.Task;

import java.util.ArrayList;

public class DeleteTaskView extends Ui {
    private final Task task;
    private final String size;
    public DeleteTaskView(Task task, ArrayList<Task> taskList) {
        this.task = task;
        this.size = String.valueOf(taskList.size());
    }

    @Override
    public void display() {
        System.out.println(
        "____________________________________________________________\n" +
        "   Noted. I've removed this task:\n" +
        "   " + this.task.toString() + "\n" +
        "   Now you have " + this.size + " tasks in the list.\n" +
        "____________________________________________________________\n"
        );
    }
}
