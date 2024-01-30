package commands;


import tasks.Task;
import utils.TaskList;
import utils.UI;

public class Add implements Command {
    private final Task task;

    public Add(Task task) {
        this.task = task;
    }

    public void execute(TaskList taskList, UI ui) {
        taskList.add(task);
        ui.showAdded(task.toString(), taskList.size());
    }

    public boolean isExit() {
        return false;
    }
}
