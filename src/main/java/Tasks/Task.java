package Tasks;

/**
 * The Task class represents a generic task.
 */
public class Task {
    private final String name;
    private boolean isDone = false;

    /**
     * Constructs a Task object with the specified name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }

    /**
     * Returns a string representation of the task for logging purposes.
     *
     * @return A string representation of the task for logging purposes.
     */
    public String logString() {
        return '|' + (isDone ? "Y" : "N") + '|' + name;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return task.toString().equals(this.toString());
    }
}
