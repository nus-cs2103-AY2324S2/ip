package action;

import util.TaskList;

/**
 * Represents an action to sort the tasks in a task list.
 */
public class Sort implements Action {
    private TaskList taskList;

    /**
     * Constructs a Sort object with the specified task list.
     *
     * @param taskList the task list to be sorted
     */
    public Sort(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the sorting action on the task list.
     *
     * @return a string representation of the sorted task list
     */
    @Override
    public String execute() {
        TaskList.sort();
        return "Tasks sorted!\nHere are the tasks in your list:\n" + taskList.toString();
    }
}
