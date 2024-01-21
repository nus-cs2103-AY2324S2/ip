package controller;

import model.Task;
import view.AddTaskView;

import java.util.ArrayList;

public class AddTask extends TaskCommand {
    private final Task task;
    private final ArrayList<Task> taskList;
    private final AddTaskView addTaskView;

    public AddTask(Task task, ArrayList<Task> taskList) {
        this.task = task;
        this.taskList = taskList;
        this.addTaskView = new AddTaskView(this.task);
    }
    public void execute() {
        taskList.add(task);
        addTaskView.display();
    }
}
