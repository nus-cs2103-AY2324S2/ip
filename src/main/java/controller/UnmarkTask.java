package controller;

import model.Task;
import view.MarkTaskView;
import view.UnmarkTaskView;

import java.util.ArrayList;

public class UnmarkTask extends TaskCommand {
    private final Task task;

    public UnmarkTask(int index, ArrayList<Task> taskList) {
        this.task = taskList.get(index);
    }
    public void execute() {
        this.task.unmark();
        UnmarkTaskView unmarkTaskView = new UnmarkTaskView(this.task);
        unmarkTaskView.display();
    }
}
