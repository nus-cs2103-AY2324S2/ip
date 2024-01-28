package action;
import java.io.IOException;

import task.Task;
import util.PrintUtil;
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
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void execute() throws IOException {
        this.taskList.add(this.task);
        int size = this.taskList.getSize();
        String plural = size == 1 ? "task" : "tasks";
        PrintUtil.print("Got it! Task added:\n  " + this.task + "\nNow you have "
                + size + " outstanding " + plural + ".");
    }
}
