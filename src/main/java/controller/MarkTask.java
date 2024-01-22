package controller;

import duke.Storage;
import model.Task;
import view.MarkTaskView;

import java.util.ArrayList;

public class MarkTask extends TaskCommand {
    private final Task task;
    private final ArrayList<Task> taskList;

    public MarkTask(int index, ArrayList<Task> taskList) {
        this.task = taskList.get(index);
        this.taskList = taskList;
    }
    public void execute(Storage storage) {
        this.task.mark();
        storage.update(taskList);
        MarkTaskView markTaskView = new MarkTaskView(this.task);
        markTaskView.display();
    }
}
