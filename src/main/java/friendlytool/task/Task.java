package friendlytool.task;


import friendlytool.process.Date;

/**
 * Class that manages tasks.
 */
public class Task {
    private String name;
    private boolean isDone;


    /**
     * Constructs task.
     *
     * @param name   name of the task.
     * @param isDone completed or not.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }


    /**
     * Mark the task as done.
     */
    public void mark() {
        isDone = true;
    }


    /**
     * mark the task as not done.
     */
    public void unmark() {
        isDone = false;
    }


    /**
     * Checks whether the task is done or not.
     *
     * @return boolean value.
     */
    public boolean isDone() {
        return this.isDone;
    }


    /**
     * Provides name of the task.
     *
     * @return name of the task.
     */
    public String getName() {
        return this.name;
    }


    /**
     * Converts a task into a readable format.
     *
     * @return string format of a task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }


    /**
     * Converts a task to a save format.
     *
     * @return save format of a task.
     */
    public String toSaveFormat() {
        return "| " + (isDone ? "true" : "false") + " | " + name;
    }

    public Date getEndTime() {
        return null;
    }
}

