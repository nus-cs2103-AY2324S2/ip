package view;

import model.Task;

public class AddTaskView extends Ui {
    private final Task task;
    public AddTaskView(Task task) {
        this.task = task;
    }

    @Override
    public void display() {
        System.out.println(
        "____________________________________________________________\n" +
        "   added: " + this.task.toString() + "\n" +
        "____________________________________________________________\n"
        );
    }
}
