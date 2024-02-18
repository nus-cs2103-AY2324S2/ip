package micromanager.tasks;

/**
 * Task class represents a task in the task list.
 * It is an abstract class and provides methods to manage tasks.
 */
public abstract class Task {
    protected boolean isDone;
    protected String taskDescription;

    /**
     * Constructs a Task object with the specified task description.
     *
     * @param taskDescription The description of the task.
     */
    public Task(String taskDescription) {
        this.isDone = false;
        this.taskDescription = taskDescription;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Checks if the task description contains a specified string (case-insensitive).
     *
     * @param s The string to search for in the task description.
     * @return true if the task description contains the string, false otherwise.
     */
    public boolean match(String s) {
        return taskDescription.toLowerCase().contains(s);
    }

    /**
     * Converts the task to a string representation for saving to a file.
     *
     * @return A string representation of the task for saving to a file.
     */
    public abstract String toFileString();
}
