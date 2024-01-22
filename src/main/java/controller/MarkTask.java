package controller;

import model.Task;
import view.MarkTaskView;
import view.UnmarkTaskView;

import java.util.ArrayList;

public class MarkTask extends TaskCommand {
    private final Task task;

    public MarkTask(int index, ArrayList<Task> taskList) {
        this.task = taskList.get(index);
    }
    public void execute() {
        this.task.mark();
        MarkTaskView markTaskView = new MarkTaskView(this.task);
        markTaskView.display();
    }
}
