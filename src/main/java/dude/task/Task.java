package dude.task;

/**
 * Task parent class.
 */
public abstract class Task {
    private boolean isDone = false;
    private final String name;

    /**
     * Creates task object.
     * @param name Name of task.
     */
    public Task(String name) {

        this.name = name;
    }

    /**
     * Creates task object.
     * @param name Name of task.
     * @param isDone Whether task is done.
     */
    public Task(String name, boolean isDone) {

        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns representative string for storage.
     * @return String to be used in the storage file.
     */
    public String getStorageString() {
        return String.format("%s | %s", this.isDone ? "1" : "0", this.name);
    };

    /**
     * Marks task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    public boolean contains(String keyword) {
        return this.name.contains(keyword);
    }

    @Override
    public String toString() {
        String checkbox = "[" + (this.isDone ? "x" : " ") + "] ";
        return checkbox + this.name;
    }
}
