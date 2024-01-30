package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of a marked or unmarked task
     * @return a string
     */
    //mark X on done tasks
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Updates the boolean value of isDone to true
     */
    public void check() {
        this.isDone = true;
    }


    /**
     * Updates the boolean value of isDone to false
     */
    public void uncheck() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task
     * @return
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]";
    }
}