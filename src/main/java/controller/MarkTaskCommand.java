package controller;

import duke.Storage;
import model.Task;
import model.TaskList;
import view.MarkTaskView;

public class MarkTaskCommand extends Command {
    private final Task task;
    private final TaskList taskList;

    public MarkTaskCommand(int index, TaskList taskList) {
        this.task = taskList.get(index);
        this.taskList = taskList;
    }

    @Override
    public void execute(Storage storage) {
        this.task.mark();
        storage.update(taskList.getTaskList());
        MarkTaskView markTaskView = new MarkTaskView(this.task);
        markTaskView.display();
    }
}
