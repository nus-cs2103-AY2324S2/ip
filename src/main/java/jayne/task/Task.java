package jayne.task;
import jayne.JayneException;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
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
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    public static Task fromFileFormat(String line) throws JayneException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new JayneException("Invalid line format");
        }
        Task task = new Task(parts[2]);
        if ("1".equals(parts[1])) {
            task.markAsDone();
        }
        return task;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
