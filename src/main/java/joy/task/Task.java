package joy.task;

/**
 * Represents a task in the task list.
 * A task can be a Todo, Deadline, or Event.
 */
public class Task {
    private static int numOfTasks = 0;
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numOfTasks++;
    }

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * @return A string representation of the task for saving to a file.
     */
    public String toFileString() {
        return "";
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Decrements the total number of tasks.
     */
    public static void decrementTotal() {
        numOfTasks--;
    }

    /**
     * Gets the total number of tasks.
     *
     * @return The total number of tasks.
     */
    public static int getNumOfTasks() {
        return numOfTasks;
    }

    /**
     * Toggles the status of the task (done or not done).
     */
    public void setStatus() {
        this.isDone = !isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    public String toString() {

        return ("[" + this.getStatusIcon() + "]" + " " + this.description);
    }

    /**
     * Returns description of the task.
     *
     * @return A description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param t The task to be checked
     * @return if the task is a duplicate
     */

    public boolean equals(Task t) {
        if (t == null) {
            return false;
        }

        return t.toString().equals(this.toString());
    }
}
