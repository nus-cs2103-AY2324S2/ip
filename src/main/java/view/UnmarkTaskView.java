package view;

import model.Task;

public class UnmarkTaskView extends Ui {
    private final Task task;
    public UnmarkTaskView(Task task) {
        this.task = task;
    }

    @Override
    public void display() {
        System.out.println(
            "____________________________________________________________\n"
            + " OK, I've marked this task as not done yet:\n"
            + "   " + this.task + "\n"
            + "____________________________________________________________\n"
        );
    }
}
