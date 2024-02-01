package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public String getStatusIcon() {
        return " "; // Default to empty space
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "T"; // Commands.Todo tasks have "T" as their status icon
    }
}

class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        return "D"; // Commands.Deadline tasks have "D" as their status icon
    }
    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }
}

class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatusIcon() {
        return "E"; // Commands.Event tasks have "E" as their status icon
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }
}
