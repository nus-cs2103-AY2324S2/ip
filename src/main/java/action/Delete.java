package action;
import java.io.IOException;

import task.Task;
import util.PrintUtil;
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

    @Override
    public void execute() throws IOException {
        Task task = this.taskList.delete(this.idx);
        int size = this.taskList.getSize();
        String plural = size == 1 ? "task" : "tasks";
        PrintUtil.print("Got it! Task deleted:\n  " + task + "\nNow you have "
                + size + " " + plural + " remaining.");
    }
}
