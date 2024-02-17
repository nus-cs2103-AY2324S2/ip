package action;

import util.TaskList;

/**
 * Represents an action to list all tasks in a task list.
 */
public class List implements Action {
    private TaskList taskList;

    /**
     * Constructs a List action with the specified task list.
     *
     * @param taskList the task list to be listed
     */
    public List(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the List action by printing all tasks in the task list.
     *
     * @return A string representing the result of the execution.
     */
    public String execute() {
        assert taskList != null : "Task list cannot be null";
        String result = "Here are the tasks in your list:\n" + taskList.toString();
        assert result != null : "Result cannot be null";
        return result;
    }
}
