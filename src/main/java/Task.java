import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String task;
    private boolean completed;

    public Task(String task) {
        this.task = task;
        this.completed = false;
    }

    // To mark the task as completed
    public void mark() {
        this.completed = true;
    }

    // To mark the task as uncompleted
    public void unmark() {
        this.completed = false;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return " | X | " + this.task;
        } else {
            return " |   | " + this.task;
        }
    }
}