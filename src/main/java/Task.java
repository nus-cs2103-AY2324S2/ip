public class Task {
    private String description;
    private boolean done;

    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDoneMarker() {
        return done ? "[X]" : "[]";
    }

    @Override
    public String toString() {
        return String.format("%s %s", getDoneMarker(), description);
    }
}