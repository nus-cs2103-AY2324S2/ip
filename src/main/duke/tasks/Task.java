package duke.tasks;

/**
 * An encapsulation of a task.
 *
 * @author Lim Zi Jia
 */
public abstract class Task {
    /** The name of the task */
    protected final String name;
    /** Represents if a task is done */
    protected boolean done;

    /**
     * Constructor for Task.
     * @param done True if the task is done.
     * @param name Name of the Task.
     */
    public Task(boolean done, String name) {
        this.done = done;
        this.name = name;
    }

    /**
     * Constructor for Task.
     * @param name Name of the Task.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.done = false;
    }

    /**
     * An abstract method that asks subclasses to be able to convert themselves into a format that is used for saving.
     *
     * @return The string of the saved object.
     */
    public abstract String toSavedString();

    public boolean has(String toFind) {
        return this.name.contains(toFind);
    }
}
