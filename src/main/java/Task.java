public class Task {
    private String description;
    private boolean isDone = false;
    private char taskType;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String toStringForStore() {
        if (this.isDone) {
            return " | 1 | " + this.description;
        } else {
            return " | 0 | " + this.description;
        }
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

}
