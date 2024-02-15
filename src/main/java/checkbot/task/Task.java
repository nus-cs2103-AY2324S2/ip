package checkbot.task;

import java.util.Objects;

/**
 * Represents a task in the task list.
 */
public abstract class Task {
    private final String name;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Formats the task for saving to a file.
     *
     * @return The formatted string.
     */
    public String formatForFile() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.name);
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    /**
     * Returns whether the name of the task contains the substring provided.
     * Case-insensitive.
     *
     * @param substr The substring
     * @return A boolean that represents if the name of the task contains the substring.
     */
    public boolean nameContains(String substr) {
        return this.name.toLowerCase().contains(substr.toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(name, task.name);
    }
}
