package controller;

import duke.Storage;
import model.Task;
import model.TaskList;
import view.UnmarkTaskView;

public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final Task task;
    private final TaskList taskList;

    public UnmarkTaskCommand(int index, TaskList taskList) {
        this.task = taskList.get(index);
        this.taskList = taskList;
    }

    @Override
    public void execute(Storage storage) {
        this.task.unmark();
        storage.update(taskList.getTaskList());
        UnmarkTaskView unmarkTaskView = new UnmarkTaskView(this.task);
        unmarkTaskView.display();
    }
}
