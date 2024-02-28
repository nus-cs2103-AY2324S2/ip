package Mitsuki;

/**
 * Contains information for the creation of Task objects.
 *
 * @author Tee Chu Jie
 */
public class Task {
    /**
     * The description of the Task object to be created.
     */
    protected String description;

    /**
     * The completion status of the task.
     * When set to true, the task is completed.
     * When set to false, the task is incomplete.
     * All task objects are incomplete when created.
     */
    protected boolean isDone;

    /**
     * The constructor for a Task object.
     *
     * @param description The description for the Task object to be created.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon 'X' if task is incomplete.
     * Returns an icon ' ' if task is complete.
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as complete by setting 'isDone' to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as incomplete by setting 'isDone' to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Converts the Task object into a human-readable String to be displayed to the user.
     * @return String object that represents the Task object.
     */
    @Override
    public String toString() {
        String status = this.getStatusIcon();
        return "["+ status + "] " + description;
    }
}
