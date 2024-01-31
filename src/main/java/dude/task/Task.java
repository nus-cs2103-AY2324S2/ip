package dude.task;

public abstract class Task {
    private boolean done = false;
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
     * @param done Whether task is done.
     */
    public Task(String name, boolean done) {

        this.name = name;
        this.done = done;
    }

    /**
     * Returns representative string for storage.
     * @return String to be used in the storage file.
     */
    public String getStorageString() {
        return String.format("%s | %s", this.done ? "1" : "0", this.name);
    };

    /**
     * Marks task as done.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks task as undone.
     */
    public void unmark() {
        this.done = false;
    }

    public boolean contains(String keyword) {
        return this.name.contains(keyword);
    }

    @Override
    public String toString() {
        String checkbox = "[" + (this.done ? "x" : " ") + "] ";
        return checkbox + this.name;
    }
}
