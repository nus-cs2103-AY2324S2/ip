package ficin.task;

/**
 * The Task class represents a general task with a description and a completion status.
 * It is a base class for specific task types like Todo, Deadline, and Event.
 */
public class Task {
    protected String description; // Changed to private for encapsulation
    protected boolean isDone; // Changed to private for encapsulation

    /**
     * Constructs a Task object with a description and initializes it as not done.
     *
     * @param description A description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done by setting the completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done by setting the completion status to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status number of the task (0 for not done, 1 for done).
     *
     * @return A string representing the task's completion status.
     */
    public int getStatusNumber() {
        return (isDone ? 1 : 0); // Use "1" for done, "0" for not done
    }

    /**
     * Returns the status icon of the task ("[X]" for done, "[ ]" for not done).
     *
     * @return A string representing the task's completion status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Converts the Task object to a string that can be saved to a file.
     * Subclasses (Todo, Deadline, Event) should implement this method.
     *
     * @return An empty string, to be implemented in each subclass.
     */
    public String toFileString() {
        // This base method is intended to be overridden by subclasses.
        return ""; // Subclass responsibility
    }

    /**
     * Returns a string representation of the task in a user-friendly format.
     *
     * @return A string representing the task's status icon and description.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }
}
