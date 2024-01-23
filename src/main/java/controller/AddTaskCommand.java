package controller;

import duke.Storage;
import model.Task;
import model.TaskList;
import view.AddTaskView;

public class AddTaskCommand extends Command {
    public static final String TODO = "todo";
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";
    private final Task task;
    private final TaskList taskList;

    public AddTaskCommand(Task task, TaskList taskList) {
        this.task = task;
        this.taskList = taskList;
    }
    @Override
    public void execute(Storage storage) {
        taskList.add(task);
        storage.update(taskList.getTaskList());
        AddTaskView addTaskView = new AddTaskView(this.task, this.taskList);
        addTaskView.display();
    }
}
