package hal.command;

import hal.task.TaskList;

public abstract class Command {
    public abstract String execute(TaskList taskList);

    public boolean isExit() {
        return false;
    }
}
