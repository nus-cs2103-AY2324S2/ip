package controller;

import model.Task;
import view.DeleteTaskView;

import java.util.ArrayList;

public class DeleteTask extends TaskCommand {
    private final Task task;
    private final ArrayList<Task> taskList;

    public DeleteTask(int index, ArrayList<Task> taskList) {
        this.task = taskList.get(index);
        this.taskList = taskList;
    }
    public void execute() {
        taskList.remove(task);
        DeleteTaskView deleteTaskView = new DeleteTaskView(this.task, this.taskList);
        deleteTaskView.display();
    }
}
