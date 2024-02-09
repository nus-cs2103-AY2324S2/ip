package action;

import java.io.IOException;

import task.Task;
import util.TaskList;

/**
 * Represents an action to add a task to a task list.
 */
public class Add implements Action {
    private Task task;
    private TaskList taskList;

    /**
     * Constructs an Add action with the specified task and task list.
     *
     * @param task     The task to be added.
     * @param taskList The task list to add the task to.
     */
    public Add(Task task, TaskList taskList) {
        this.task = task;
        this.taskList = taskList;
    }

    /**
     * Executes the Add action by adding the task to the task list and printing the updated task count.
     *
     * @return A string representing the result of the execution.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String execute() throws IOException {
        StringBuilder sb = new StringBuilder();
        assert this.task != null : "Task cannot be null"; // Add assertion for task
        assert this.taskList != null : "TaskList cannot be null"; // Add assertion for taskList
        this.taskList.add(this.task);
        int size = this.taskList.getSize();
        String plural = size == 1 ? "task" : "tasks";
        sb.append("Got it! Task added:\n  ").append(task).append("\nNow you have ").append(size)
                        .append(" outstanding ").append(plural).append(".");
        return sb.toString();
    }
}
