package controller;

import duke.Storage;
import model.Task;
import view.UnmarkTaskView;

import java.util.ArrayList;

public class UnmarkTask extends TaskCommand {
    private final Task task;
    private final ArrayList<Task> taskList;

    public UnmarkTask(int index, ArrayList<Task> taskList) {
        this.task = taskList.get(index);
        this.taskList = taskList;
    }
    public void execute(Storage storage) {
        this.task.unmark();
        storage.update(taskList);
        UnmarkTaskView unmarkTaskView = new UnmarkTaskView(this.task);
        unmarkTaskView.display();
    }
}
