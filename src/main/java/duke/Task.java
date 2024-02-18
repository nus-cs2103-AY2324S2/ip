package duke;

/**
 * Represents a task with the specified description.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task object with the specified description.
     *
     * @param description Task description.
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
    public String markAsDone() {
        this.isDone = true;
        assert this.getStatusIcon() == "X" : "Status Icon should be X";
        String result = "Nice! I've marked this task as done:\n" + "  " + this.toString();
        System.out.println(result);
        return result;
    }

    /**
     * Sets isDone to false.
     */
    public String unmark() {
        this.isDone = false;
        assert this.getStatusIcon() == " " : "Status Icon should be blank";
        String result = "OK, I've marked this task as not done yet\n" + "  " + this.toString();
        System.out.println(result);
        return result;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
