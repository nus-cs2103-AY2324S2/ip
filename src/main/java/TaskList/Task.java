package TaskList;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void setDone(boolean status) {
        this.isDone = status;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Allows saving of the Task object in a String format so that it can be stored in the database.
     *
     * @return String interpretation of Task object.
     */
    public String toStorageString() {
        return "T | " + this.getStatus() + " | " + this.description;
    }

}
