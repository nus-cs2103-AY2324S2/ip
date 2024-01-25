package duke;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unMarkAsDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + description;
    }

    public String toFileString() {
        return "T" + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    public static Task fromFileString(String str) {
        String[] parts = str.split(" \\| ");
        if (!parts[0].equals("T")) {
            return null;
        }
        String description = parts[2].trim();
        boolean isDone = parts[1].trim().equals("1");
        Task task = new Task(description);
        if (isDone) task.markAsDone();
        return task;
    }
}
