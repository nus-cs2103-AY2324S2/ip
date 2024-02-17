package junjie.tasks;

/**
 * Represents a task that can be completed.
 */
public abstract class Task {
    private final String name;
    private boolean done = false;

    /**
     * Constructs a task with the given name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructs a task with the given name and completion status.
     *
     * @param name The name of the task.
     * @param isDone The completion status of the task (true if done, false if not done).
     */
    public Task(String name, boolean isDone) {
        assert name != null : "Task name cannot be null";

        this.name = name;
        this.done = isDone;
    }

    /**
     * Marks the task as done or not done.
     *
     * @param done The completion status to set for the task (true if done, false if not done).
     */
    public void markDone(boolean done) {
        this.done = done;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the task to be displayed to the user.
     *
     * @return A string representation of the task to be displayed to the user.
     */
    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", name);
    }

    /**
     * Returns a string representation of the task to be written to the file.
     *
     * @return A string representation of the task to be written to the file.
     */
    public abstract String toFileString();
}
