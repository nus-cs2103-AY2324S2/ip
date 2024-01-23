package controller;

import duke.Storage;
import model.TaskList;
import view.TaskListView;

public class ListTaskCommand extends Command {
    private final TaskListView taskListView;

    public ListTaskCommand(TaskList taskList) {
        this.taskListView = new TaskListView(taskList);
    }

    @Override
    public void execute(Storage storage) {
        taskListView.display();
    }
}
