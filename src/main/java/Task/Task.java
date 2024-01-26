package task;

public abstract class Task {
    private String description;
    private boolean isDone;

    private String escapedDescription;

    public Task(String description) {
        this.description = description;
        this.escapedDescription = escapeDescription(description);
        this.isDone = false;
    }

    protected static String escapeDescription(String description) {
        return description.replace("|", "\\|");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "] " + description;
    }

    public String serialize() {
        return (isDone ? "1" : "0") + " | " + escapedDescription;
    }
}
