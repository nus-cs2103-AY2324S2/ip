package ghbot.executor;

import ghbot.TaskList;

public abstract class Executor {
    protected TaskList taskList;

    public void set(TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract void execute();
}