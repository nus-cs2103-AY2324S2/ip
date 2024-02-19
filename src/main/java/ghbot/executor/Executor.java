package ghbot.executor;

import ghbot.task.TaskList;

/**
 * Executor Abstract Class.
 */
public abstract class Executor {
    protected TaskList taskList;

    /**
     * Sets an instance of task list.
     * @param taskList A list of tasks.
     */
    public void set(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns a string containing the output of the respective commands.
     * @return A string containing the output of the respective commands.
     */
    public abstract String execute();
}
