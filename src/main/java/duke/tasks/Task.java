package duke.tasks;

/**
 * Task class is an abstract class that is the parent class of all tasks.
 * It contains the description of the task and a boolean to check if the task is done.
 */
public abstract class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The status of the task.
     */
    protected boolean isDone;

    /**
     * Constructor for task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    /**
     * Returns the string representation of the task to be written to file.
     * @return The string representation of the task to be written to file.
     */
    public abstract String writeToFileString();

    /**
     * Returns the status icon of the task.
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done or not done.
     * @return The output of the marking.
     */
    public String toggle() {
        String output;
        if (this.isDone) {
            this.isDone = false;
            output = " OK, I've marked this task as not done yet:\n" + "   " + this + "\n";
        } else {
            this.isDone = true;
            output = " Nice! I've marked this task as done:\n" + "   " + this + "\n";
        }
        return output;
    }

}
