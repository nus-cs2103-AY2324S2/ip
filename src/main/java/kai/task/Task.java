package kai.task;

public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a new Task object with isDone set as false.
     * First constructor.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overloaded constructor. isDone set by the user input.
     * @param description Description of the task.
     * @param isDone Task done status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsDoneStatus() {
        return (isDone ? "O" : "X");
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean done) {
        this.isDone = done;
    }

    public String formatStringForSaveFile() {
        return getIsDoneStatus() + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getIsDoneStatus() + "] " + this.description;
    }
}
