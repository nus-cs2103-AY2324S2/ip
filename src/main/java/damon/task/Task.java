package damon.task;


/**
 * Represents a task by description and status of task.
 * Status is a boolean object to represent whether this task is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task object with only String description parameter..
     *
     * @param description Description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task object with
     * both String description parameter and boolean isDone parameter.
     *
     * @param description Description of Task.
     * @param isDone Status of Task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the icon of Task status.
     * "X" means Task is done, while " " means Task is not done.
     *
     * @return Icon of Task status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets boolean idOne parameter to be true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets boolean idOne parameter to be false.
     */
    public void markBackNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a sentence containing status and description of Task.
     *
     * @return Sentence representing Task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public boolean isContainKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    //...
}

