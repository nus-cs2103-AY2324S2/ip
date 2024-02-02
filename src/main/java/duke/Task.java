package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String action;
    private Boolean isDone;



    public Task(String action, Boolean isDone) {
        this.action = action;
        this.isDone = isDone;
    }
    @Override
    public String toString() {
        if (isDone) {
            return "[X]" + action;
        } else {
            return "[ ]" + action;
        }
    }
    public void mark() {
        isDone = true;
    }
    public void unmark() {
        isDone = false;
    }

    public String export() {
        return toString();
    }
}
class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    public Event(String input, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(input, isDone);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        String s = super.toString();
        return "[E]" + s + "(from " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:ss")).replace("T", " ") + " to " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:ss")).replace("T", " ") + ")";
    }
    @Override
    public String export() {
        String s = super.toString();
        return "[E]" + s + "/from" + from.toString().replace("T", " ") + "/to" + to.toString().replace("T" , " ") ;
    }
}
class Todo extends Task {
    public Todo(String input, boolean isDone) {
        super(input, isDone);
    }
    @Override
    public String toString() {
        String s = super.toString();
        return "[T]" + s;
    }
    @Override
    public String export() {
        return toString();
    }
}
class Deadline extends Task {
    private LocalDateTime by;
    public Deadline(String input, boolean isDone, LocalDateTime by) {
        super(input, isDone);
        this.by = by;
    }
    @Override
    public String toString() {
        String s = super.toString();
        return "[D]" + s + "(by:" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:ss")).replace("T", " ") +")";
    }

    @Override
    public String export() {
        String s = super.toString();
        return "[D]" + s + "/by" + by.toString().replace("T", " ") ;
    }
}
