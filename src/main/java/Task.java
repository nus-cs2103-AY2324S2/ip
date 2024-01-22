abstract class Task {
    private String description;
    private boolean isDone;
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    public abstract String getTypeIcon();

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + " " + this.getDescription();
    }
}

