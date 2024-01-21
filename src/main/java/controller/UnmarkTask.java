package controller;

import model.Task;
import view.MarkTaskView;
import view.UnmarkTaskView;

import java.util.ArrayList;

public class UnmarkTask extends TaskCommand {
    private final Task task;
    private final UnmarkTaskView unmarkTaskView;

    public UnmarkTask(int index, ArrayList<Task> taskList) {
        this.task = taskList.get(index);
        this.unmarkTaskView = new UnmarkTaskView(this.task);
    }
    public void execute() {
        this.task.unmark();
        unmarkTaskView.display();
    }
}
