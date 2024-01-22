package controller;

import duke.Storage;
import model.Task;
import view.TaskListView;

import java.util.ArrayList;

public class ListTask extends TaskCommand{
    private final TaskListView taskListView;

    public ListTask(ArrayList<Task> taskList) {
        this.taskListView = new TaskListView(taskList);
    }

    public void execute(Storage storage) {
        taskListView.display();
    }
}
