package haro.task;

/**
 * The Task class represents a generic task in the application.
 * It is an abstract class providing a common structure for various task types.
 */
public abstract class Task {
    protected String task;
    protected boolean done;

    /**
     * Constructs a Task instance with the specified task description.
     *
     * @param task Task description
     */
    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Constructs a Task instance with the specified task description.
     *
     * @param task Task description
     */
    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkTask() {
        this.done = false;
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return String representation of the task
     */
    public String printTask() {
        String taskString;
        if (this.done) {
            taskString = "[X] " + this.task;
        } else {
            taskString = "[ ] " + this.task;
        }

        return taskString;
    }

}
