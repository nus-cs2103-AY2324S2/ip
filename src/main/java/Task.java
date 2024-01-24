import java.util.*;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // update task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // update status of task as not done
    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + getDescription();
    }
}

// Todo Class
class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

// Deadline Class
class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

// Event Class
class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to +")";
    }
}
