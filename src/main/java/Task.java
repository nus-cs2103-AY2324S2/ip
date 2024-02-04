/**
 * Represents a task with the specified description.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task object with the specified description.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Gets status of Task.
     *
     * @return X if Task is done, space character otherwise.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Sets isDone to true.
     */
    public void markAsDone() {
        Duke.breakLine();
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + this.toString());
        Duke.breakLine();
    }

    /**
     * Sets isDone to false.
     */
    public void unmark() {
        Duke.breakLine();
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet");
        System.out.println("  " + this.toString());
        Duke.breakLine();
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
