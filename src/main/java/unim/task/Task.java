package unim.task;

/**
 * Task - Represents a basic task with a description and completion status.
 */

public class Task {
    protected String userInput;
    protected boolean isDone;


    /**
     * Creates a Task with the specified input description and sets completion status to false.
     *
     * @param input The description of the task.
     */
    public Task(String input) {
        this.userInput = input;
        this.isDone = false;
    }


    /**
     * Gets the status icon based on the completion status.
     *
     * @return The status icon ("[X]" if done, "[ ]" if not done).
     */
    public String getStatusIcon() {
        return (isDone ?  "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Unmarks the task, setting completion status to false.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    public String getDescription() {
        return userInput;
    }

    @Override
    public String toString() {
        return getStatusIcon() + userInput;
    }
}