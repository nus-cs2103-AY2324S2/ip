package controller;

import duke.Storage;
import model.Task;
import model.TaskList;
import view.DeleteTaskView;

public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final Task task;
    private final TaskList taskList;

    public DeleteTaskCommand(int index, TaskList taskList) {
        this.task = taskList.get(index);
        this.taskList = taskList;
    }
    @Override
    public void execute(Storage storage) {
        taskList.remove(task);
        storage.update(taskList.getTaskList());
        DeleteTaskView deleteTaskView = new DeleteTaskView(this.task, this.taskList);
        deleteTaskView.display();
    }
}
