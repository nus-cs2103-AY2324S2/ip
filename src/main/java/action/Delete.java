package action;

import java.io.IOException;

import task.Task;
import util.TaskList;

/**
 * Represents an action to delete a task from the task list.
 */
public class Delete implements Action {
    private final TaskList taskList;
    private final int idx;

    /**
     * Constructs a Delete action with the specified task list and index.
     *
     * @param taskList the task list to delete the task from
     * @param idx the index of the task to be deleted
     */
    public Delete(TaskList taskList, int idx) {
        this.taskList = taskList;
        this.idx = idx;
    }

    /**
     * Executes the Delete action by adding the task to the task list and printing the updated task count.
     *
     * @return A string representing the result of the execution.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String execute() throws IOException {
        assert taskList != null : "Task list cannot be null";
        assert idx >= 0 && idx < taskList.getSize() : "Invalid index";

        Task task = this.taskList.delete(this.idx);
        int size = this.taskList.getSize();
        String plural = size == 1 ? "task" : "tasks";
        return new StringBuilder()
                .append("Got it! Task deleted:\n")
                .append("  ").append(task)
                .append("\n").append("Now you have ")
                .append(size).append(" ").append(plural)
                .append(" remaining.").toString();
    }
}
