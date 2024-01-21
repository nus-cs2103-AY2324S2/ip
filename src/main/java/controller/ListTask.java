package controller;

import model.Task;
import view.TaskListView;

import java.util.ArrayList;

public class ListTask {
    private final TaskListView taskListView;

    public ListTask(ArrayList<Task> taskList) {
        this.taskListView = new TaskListView(taskList);
    }

    public void execute() {
        taskListView.display();
    }
}
