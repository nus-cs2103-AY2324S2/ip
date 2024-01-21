import java.lang.StringBuilder;
import java.util.ArrayList;

/**
 * Class to track and manage tasks.
 *
 * @author KohGuanZeh
 */
public class TaskList {
    // Store list of tasks.
    private ArrayList<Task> taskList;

    /**
     * Creates a new instance of TaskList.
     * Initial capacity is set to 100.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Adds the specified Task and returns a message of the added Task.
     *
     * @param task Task to add.
     * @return Message stating the added task.
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        return "Added: " + task.getTaskInformation() + "\n" + this.getTotalTasks();
    }

    /**
     * Removes the task at specified and returns a message of the deleted Task.
     *
     * @param index Index of task to delete.
     * @return Message stating the deleted task.
     */
    public String deleteTask(int index) throws TaskException {
        if (index <= 0 || index > this.taskList.size()) {
            throw new TaskException("Error. Task of index " + index + " cannot be found.");
        }
        Task removedTask = this.taskList.remove(index - 1);
        return "Removed: " + removedTask.getTaskInformation() + "\n" + this.getTotalTasks();
    }

    /**
     * Returns the total number of tasks tracked.
     *
     * @return Message of total number of tasks tracked.
     */
    public String getTotalTasks() {
        return "There are now " + this.taskList.size() + " tasks in the list.";
    }

    /**
     * Sets the task at the specified index as done and returns a task completion message.
     *
     * @param index Index of task. Note that this is always 1 more than index stored in the list.
     * @return Task completion message.
     */
    public String markTask(int index) throws TaskException {
        if (index <= 0 || index > this.taskList.size()) {
            throw new TaskException("Error. Task of index " + index + " cannot be found.");
        }
        Task task = this.taskList.get(index - 1);
        task.markAsDone();
        return "Great job for finishing the task:\n" + task.getTaskInformation();
    }

    /**
     * Sets the task at the specified index as not done and returns a task incompletion message.
     *
     * @param index Index of task. Note that this is always 1 more than index stored in the list.
     * @return Task incompletion message.
     */
    public String unmarkTask(int index) throws TaskException {
        if (index <= 0 || index > this.taskList.size()) {
            throw new TaskException("Error. Task of index " + index + " cannot be found.");
        }
        Task task = this.taskList.get(index - 1);
        task.unmarkAsDone();
        return "Take your time mate. Quality over quantity:\n" + task.getTaskInformation();
    }

    /**
     * Returns the list of tasks and their completion status.
     *
     * @return String containing list of tasks and completion status.
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder("Here are your list of tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append("\n    ");
            sb.append(i + 1).append(".").append(taskList.get(i).getTaskInformation());
        }
        return sb.toString();
    }
}
