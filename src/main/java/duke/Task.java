package duke;

/**
 * Represents a generic task in the Duke application. This class is the
 * superclass for specific types of tasks (e.g., Todo, Deadline, Event).
 * It encapsulates common properties and functionalities for tasks.
 */
public abstract class Task {
    protected String description; // The task's description
    protected boolean isDone; // The task's completion status
    protected int statusNumber; // Numeric representation of the task's completion status
    protected String input; // The original input that created the task

    /**
     * Constructs a Task with the specified description and input.
     *
     * @param description The task's description.
     * @param input       The original user input that created the task.
     */
    Task(String description, String input) {
        this.description = description;
        this.isDone = false;
        this.statusNumber = 0;
        this.input = input;
    }

    /**
     * Returns a status icon indicating whether the task is done.
     *
     * @return A string representation of the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns status number where 1 represents completed, 0 represents incomplete
     *
     * @return A numeric representation of the task's completion status
     */
    public int getStatusNumber() {
        return this.statusNumber; // mark done task with X
    }

    /**
     * Returns the details of the task, including its status icon and description.
     *
     * @return A formatted string containing the task's status and description.
     */
    public String getDetails() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Marks the task as complete.
     */
    public void setComplete() {
        this.isDone = true;
        this.statusNumber = 1;
    }

    /**
     * Marks the task as incomplete.
     */
    public void setIncomplete() {
        this.isDone = false;
        this.statusNumber = 0;
    }

    /**
     * Marks the task as done and prints a confirmation message.
     */
    public String markComplete() {
        this.setComplete();
        return "\tNice! I've marked this task as done:\n\t" + this.getDetails();
    }

    /**
     * Marks the task as done without printing a confirmation message.
     */
    public void markWithoutPrint() {
        this.setComplete();
    }

    /**
     * Marks the task as not done and prints a confirmation message.
     */
    public String unmarkComplete() {
        this.setIncomplete();
        return "\tOK, I've marked this task as not done yet:\n\t" + this.getDetails();
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string containing the task's details.
     */
    @Override
    public String toString() {
        return this.getDetails();
    }

    /**
     * Converts the task to a format suitable for file storage. This method must be
     * implemented by subclasses to account for their specific properties.
     *
     * @return A string representation of the task for file storage.
     */
    public abstract String toFileFormat();
}
