package duke.tasks;

/**
 * Represents tasks.
 */
public class Task {

    private boolean isDone;
    private String name;

    /**
     * Constructs Task.
     *
     * @param name Name of task.
     * @param isDone Boolean that indicates
     *             whether task is done.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public String getIsDone() {
        String doneMark = "X";
        if (this.isDone) {
            return doneMark;
        } else {
            return " ";
        }
    }

    public void setTaskDone() {
        this.isDone = true;
    }

    public void setTaskUndone() {
        this.isDone = false;
    }

    /**
     * Returns Task in correct String format.
     *
     * @return Task in correct String format.
     */
    @Override
    public String toString() {
        return " [" + getIsDone() + "] " + this.name;
    }

    public String getType() {
        return "general";
    }

    public String getDone() {
        return Boolean.toString(this.isDone);
    }
}
