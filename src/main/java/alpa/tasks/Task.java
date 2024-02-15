package alpa.tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    private TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public TaskType getType() {
        return type;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[ X ]" : "[   ]");
    }

    public String toFileFormat() {
        String status = isDone ? "1" : "0";
        return getType().getShortName() + " | " + status + " | " + description;
    }
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
