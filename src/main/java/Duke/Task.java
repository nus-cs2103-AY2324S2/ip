package duke;


/**
 * This class represents a task.
 * Each task has a name and a status indicating whether it is done.
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructs a new Task with the specified name.
     * The new task is initially not done.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The returned string includes the task status and the name.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        String check = isDone ? "[X] " : "[ ] ";
        return check + this.name;
    }

    /**
     * Returns the name of the task.
     *
     * @return the name of the task
     */
    public String toString2() {
        return this.name;
    }

    /**
     * Marks the task as done.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void undone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }
}