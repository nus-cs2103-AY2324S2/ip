package pew;

public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;

    /**
     * Constructor for the Task class
     *
     * @param index the index of the task
     * @param description the description of the task
     */
    public Task(int index, String description) {
        this.index = index + 1;
        this.description = description;
        this.isDone = false;
    }

    /**
     * marks the task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * marks the task as not done
     */
    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTask() {
        return index + ". [" + getStatusIcon() + "] " + description;
    }

    public String getDescription() {
        return description;
    }
    /**
     * change the index of the task
     *
     * @param newIndex the new index of the task
     */
    public void changeindex(int newIndex) {
        this.index = newIndex;
    }

    /**
     * saves the task as a string
     *
     * @return the string representation of the task
     */
    public String save() {
        return index + "|" + (isDone ? "1" : "0") + "|" + description;
    }
}
