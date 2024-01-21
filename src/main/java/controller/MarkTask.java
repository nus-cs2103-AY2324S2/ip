package controller;

import model.Task;
import view.MarkTaskView;

import java.util.ArrayList;

public class MarkTask extends TaskCommand {
    private final Task task;
    private final MarkTaskView markTaskView;

    public MarkTask(int index, ArrayList<Task> taskList) {
        this.task = taskList.get(index);
        this.markTaskView = new MarkTaskView(this.task);
    }
    public void execute() {
        this.task.mark();
        markTaskView.display();
    }
}
