package duke.task;

/**
 * Class representing a generic Task
 */
public class Task {
    protected String name;
    protected boolean isDone;

    protected String tag;

    /**
     * Creates a Task
     * @param name Name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns the status icon which indicates if task is done
     * @return The status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Adds a tag to the task.
     */
    public void tag(String tag){
        this.tag = tag;
    }

    /**
     * Formats the task as a string to be saved in Storage.
     * @return The formatted string
     */
    public String fileString() {
        return (isDone ? "1 | " : "0 | ") + this.name + " | " +
            (this.tag == null ? "" : this.tag);
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.name +
                (this.tag == null ? "" : " #" + this.tag);
    }
}

