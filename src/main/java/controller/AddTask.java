package controller;

import model.Task;
import view.AddTaskView;

import java.util.ArrayList;

public class AddTask extends TaskCommand {
    private final Task task;
    private final ArrayList<Task> taskList;

    public AddTask(Task task, ArrayList<Task> taskList) {
        this.task = task;
        this.taskList = taskList;
    }
    public void execute() {
        taskList.add(task);
        AddTaskView addTaskView = new AddTaskView(this.task, this.taskList);
        addTaskView.display();
    }
}
