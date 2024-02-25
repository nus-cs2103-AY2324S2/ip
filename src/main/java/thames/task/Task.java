package thames.task;

/**
 * Class for tasks.
 */
public class Task {
    /** Name of task */
    protected String name;
    /** True if task is done */
    protected Boolean isDone;
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getDone() {
        return this.isDone;
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    public Boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + (isDone? "X":" ") + "] " + this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            return ((Task) o).name.equals(this.name) &&
                    ((Task) o).isDone.equals(this.isDone);
        }
        return false;
    }

}
