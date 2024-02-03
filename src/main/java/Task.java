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

class ToDo extends Task {
    public ToDo(String todo) {
        super(todo);
    }

    @Override
    public String toString() {
        return "T" + super.toString();
    }
}

class Deadline extends Task {
    private LocalDateTime by;
    public Deadline (String deadline, String by) {
        super(deadline);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.by = LocalDateTime.parse(by, inputFormatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return "D" + super.toString() + " | " + this.by.format(outputFormatter);
    }
}

class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    public Event(String event, String from, String to) {
        super(event);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return "E" + super.toString() + " | " + this.from.format(outputFormatter) + " - " + this.to.format(outputFormatter);
    }
}