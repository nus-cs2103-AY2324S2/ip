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
     * Returns a message that states the added task.
     *
     * @return Message stating the added task.
     */
    public String addTask(String taskDescription) {
        Task task = new Task(taskDescription);
        this.taskList.add(task);
        return "Added: " + this.taskList.size() + "." + task.getTaskInformation();
    }

    /**
     * Sets the task at the specified index as done and returns a task completion message.
     *
     * @param index Index of task. Note that this is always 1 more than index stored in the list.
     * @return Task completion message.
     */
    public String markTask(int index) {
        Task task = this.taskList.get(index - 1);
        task.markAsDone();
        return "Great job for finishing the task:\n" + index + "." + task.getTaskInformation();
    }

    /**
     * Sets the task at the specified index as not done and returns a task incompletion message.
     *
     * @param index Index of task. Note that this is always 1 more than index stored in the list.
     * @return Task incompletion message.
     */
    public String unmarkTask(int index) {
        Task task = this.taskList.get(index - 1);
        task.unmarkAsDone();
        return "Take your time mate. Quality over quantity:\n" + index + "." + task.getTaskInformation();
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
