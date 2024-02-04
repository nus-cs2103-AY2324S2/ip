package duke.tasks;

/**
 * Represents tasks.
 */
public class Task {

    private boolean isDone = false;
    private String name = "";

    /**
     * Constructs Task.
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.isDone = done;
    }

    public String getName() {
        return this.name;
    }

    public String setIsDone() {
        if (this.isDone) {
            return "X";
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
        return " [" + setIsDone() + "] " + this.name;
    }

    public String getType() {
        return "general";
    }

    public String getDone() {
        return Boolean.toString(this.isDone);
    }
}
