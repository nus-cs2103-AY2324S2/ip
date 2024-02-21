package irwyn.tasks;

/**
 * This class encapsulates the Task class.
 * It represents a Task.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    private TaskType task;

    /**
     * List for different commands for a task.
     */
    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    /**
     * Constructor for a Task object.
     *
     * @param description The description of the task.
     * @param taskType The task type of the task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.task = taskType;
    }

    /**
     * Marks the task to be done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task to be not done.
     */
    public void unmark() {
        this.isDone = false;
    }


    /**
     * This method returns the status icon of the task.
     * If the task is done, it returns "X", otherwise it returns " ".
     *
     * @return A string that represents the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * This method returns the file format of the task.
     * If the task is done, it returns "1", otherwise it returns "0".
     * The description of the task is appended after " | ".
     *
     * @return A string that represents the file format of the task.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * This method returns the string representation of the task.
     * The status icon and the description of the task are included in the string.
     *
     * @return A string that represents the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * This method returns a string that acknowledges the addition of the task.
     * It includes the task, and the total number of tasks in the list.
     *
     * @param numberList The total number of tasks in the list.
     * @return A string that acknowledges the addition of the task.
     */
    public String replyString(int numberList) {
        return "Got it. I've added this task:\n  "
                + this + "\n"
                + "Now you have " + numberList + " tasks in the list.\n";
    }

    /**
     * Method to compare this object with another object when sorting.
     *
     * @param otherTask The other task to compare to.
     * @return Int to returned to indicate which string comes first lexicographically.
     */
    @Override
    public int compareTo(Task otherTask) {
        return this.toString().compareTo(otherTask.toString());
    }

    /**
     * Method to get the description of the task.
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }
}
