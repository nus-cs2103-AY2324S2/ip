package arona;

/**
 * Stores the task description and indicates if the task is completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Process the string with unmodified date form which is to be saved in the file.
     *
     * @return String that is to be processed and stored in the file.
     */
    public String userInputToString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setStatusIcon(boolean status) {
        this.isDone = status;
    }

}