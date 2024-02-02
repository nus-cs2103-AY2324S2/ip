package tasks;

/**
 * Represents a general task in the task management application.
 * This class serves as a base for specific types of tasks like ToDo, Deadline, and Event.
 */
public class Task {
    protected String description; // The description of the task
    protected boolean isDone; //The completion status of the task

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return The task's completion status.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task's status icon.
     * Completed tasks are marked with "[X]" and incomplete tasks with "[ ]".
     *
     * @return A string representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Marks the task as done or not done based on the input.
     * Prints a message indicating the action taken.
     *
     * @param start Indicates whether the marking is being done at the start (initial loading).
     */
    public void markDone(boolean start) {
        if (this.isDone) {
            System.out.println("Stop yappin' bruh... tasks.Task is already marked as done");
        } else {
            this.isDone = true;
            if (!start) {
            System.out.println("Good job Yapper! I've marked this task as done:");
            }
        }
        if (!start) {
            System.out.println(this);
        }
    }

    /**
     * Unmarks the task as done, setting its status to incomplete.
     * Prints a message indicating the action taken.
     */
    public void unmarkDone() {
        if (!this.isDone) {
            System.out.println("Stop yappin' bruh... tasks.Task remains incomplete");
        } else {
            this.isDone = false;
            System.out.println("YAPYAP! I've unmarked this task...");
        }
        System.out.println(this);
    }

    /**
     * Returns a string formatted for file storage.
     * This method is intended to be overridden by subclasses.
     *
     * @return A string suitable for storing the task in a file.
     */
    public String toFileFormat() {
        return "";
    }

}
