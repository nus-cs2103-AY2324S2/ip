package ghbot.executor;

import ghbot.task.TaskList;

/**
 * Executor Abstract Class.
 */
public abstract class Executor {
    protected TaskList taskList;

    public void set(TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract String execute();
}
