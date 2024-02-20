package paimon.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Create String for Local Storage from Task fields.
     * @return String to be used for Local Storage.
     */
    public String createSaveData() {
        return String.format("T | %d | %s\n", ((this.isDone) ? 1 : 0), this.description);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Checks if the task description contains the keyword regardless of Upper/Lowercase.
     * @param keyword the keyword to be searched.
     * @return boolean whether the description contains.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }
}
