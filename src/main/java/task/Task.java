package task;

/**
 * Task is an object created to identify the task inputted by the user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    private String indent = "    ";

    /**
     * The constructor of Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A getter function to get the icon status.
     *
     * @return A "X" if task is done and " " if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * A getter function to get the description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Mark a specific task
     */
    public void markAsDone() {
        if (isDone) {
            System.out.println(indent + "This task is already done");
        } else {
            isDone = true;;
        }
    }

    /**
     * Unmark a specific task
     */
    public void markAsUndone() {
        if (isDone) {
            isDone = false;;
        } else {
            System.out.println(indent + "This task is not done yet");
        }
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }
}
