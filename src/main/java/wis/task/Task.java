package wis.task;

/**
 * An abstract class representing all types of tasks entered
 * by users.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }


    /**
     * Checks whether description of the task contains some keyword.
     */
    public boolean hasPattern(String pattern) {
        return this.description.contains(pattern);
    }

    /**
     * Returns a string describing the task to display to users.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.isDone? "X" : " ",
                this.description);
    }

    /**
     * Returns a string to store in hard disk. The string will contain
     * all essential information about the task. Uses #!# as separators.
     */
    public String toSavedString() {
        return String.format("%d#!#%s",
                this.isDone? 1 : 0,
                this.description);
    }
}
