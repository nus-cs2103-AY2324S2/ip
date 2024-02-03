package podz.command;

import podz.task.TaskList;
import podz.ui.Ui;

public abstract class Command {
    protected TaskList taskList;

    public void setTasks (TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract void execute(Ui ui);
}