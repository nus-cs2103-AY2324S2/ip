package view;

import model.Task;

public class MarkTaskView extends Ui {
    private final Task task;
    public MarkTaskView(Task task) {
        this.task = task;
    }

    @Override
    public void display() {
        System.out.println(
        "____________________________________________________________\n" +
        " Nice! I've marked this task as done:\n" +
        "   " + this.task.isDoneString() + " " + this.task + "\n" +
        "____________________________________________________________\n"
        );
    }
}
