package haro.task;

/**
 * The Task class represents a generic task in the application.
 * It is an abstract class providing a common structure for various task types.
 */
public abstract class Task {
    protected String task;
    protected boolean isDone;

    /**
     * Constructs a Task instance with the specified task description.
     *
     * @param task Task description
     */

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Constructs a Task instance with the specified task description.
     *
     * @param task Task description
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return String representation of the task
     */
    public String printTask() {
        String taskString;
        if (this.isDone) {
            taskString = "[X] " + this.task;
        } else {
            taskString = "[ ] " + this.task;
        }

        return taskString;
    }

    public boolean isFound(String searchString) {
        return this.task.contains(searchString);
    }

    public void setTask(String updatedTask) {
        this.task = updatedTask;
    }

}
