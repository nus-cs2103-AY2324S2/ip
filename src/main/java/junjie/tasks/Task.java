package junjie.tasks;

/**
 * Represents a task that can be completed.
 */
public abstract class Task {
    private final String name;
    private final String[] tags;
    private boolean done = false;

    /**
     * Constructs a task with the given name and tags.
     *
     * @param name The name of the task.
     * @param tags The tags of the task.
     */
    public Task(String name, String[] tags) {
        this.name = name;
        this.tags = tags;
    }

    /**
     * Constructs a task with the given name, tags, and done status.
     *
     * @param name The name of the task.
     * @param tags The tags of the task.
     * @param isDone The done status of the task.
     */
    public Task(String name, String[] tags, boolean isDone) {
        assert name != null : "Task name cannot be null";

        this.name = name;
        this.tags = tags;
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
     * Returns the tags of the task.
     *
     * @return The tags of the task.
     */
    public String[] getTags() {
        return tags;
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
        return String.format("[%s] %s %s",
                done ? "X" : " ",
                name,
                tags.length == 0 ? "" : String.format(" (%s)", String.join(", ", tags)));
    }

    /**
     * Returns a string representation of the task to be written to the file.
     *
     * @return A string representation of the task to be written to the file.
     */
    public abstract String toFileString();
}
