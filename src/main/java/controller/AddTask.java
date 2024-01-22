package controller;

import duke.Storage;
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
    public void execute(Storage storage) {
        taskList.add(task);
        storage.update(taskList);
        AddTaskView addTaskView = new AddTaskView(this.task, this.taskList);
        addTaskView.display();
    }
}
