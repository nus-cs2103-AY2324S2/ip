import java.text.SimpleDateFormat;
import java.util.Date;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    // New method to create a task from a string when loading from a file
    public static Task createTaskFromFileString(String fileString) {
        String[] parts = fileString.split(" \\| ");
        if (parts.length == 3) {
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task task = new Task(description);
            if (isDone) {
                task.markAsDone();
            }
            return task;
        }
        return null; // Invalid format
    }
}