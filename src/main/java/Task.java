import java.io.Serializable;

abstract public class Task implements Serializable {
    protected final String desc;
    private boolean isCompleted;
    public abstract String toFileString();

    /**
     * Constructor for Task class.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName, boolean isCompleted) {
        this.desc = taskName;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks the task as complete.
     */
    public void markAsComplete() {
        this.isCompleted = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString() + "\n");
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        this.isCompleted = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString() + "\n");

    }

    /**
     * Returns the status of the task.
     *
     * @return "X" if the task is completed, " " otherwise.
     */
    public String getStatus() {
        return this.isCompleted
                ? "X"
                : " ";
    }

    public String getDesc() {
        return this.desc;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.desc;
    }
}
