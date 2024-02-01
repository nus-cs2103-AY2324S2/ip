package tony.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected TaskType type;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public abstract String getType();

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public String formattedString() {
        return "|" + (isDone? 1 : 0) + "|" + description;
    }

    public String getDescription() {
        return description;
    }
}






