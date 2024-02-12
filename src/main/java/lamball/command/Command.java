package lamball.command;

import lamball.TaskList;
public abstract class Command {
    TaskList taskList;

    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    abstract public boolean run();
}
