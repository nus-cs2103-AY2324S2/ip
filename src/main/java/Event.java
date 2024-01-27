import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.type = "E";
        this.from = from;
        this.to = to;
    }

    public String getStatus() {
        String statusIcon = (isDone ? "X" : " ");
        return "[" + type + "][" + statusIcon + "] " + description + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:ma")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:ma")) + ")";
    }

    public String toText() {
        return super.toText() + " / " + from.format(DateTimeFormatter.ofPattern("yyyy MM dd H m")) + " / " + to.format(DateTimeFormatter.ofPattern("yyyy MM dd H m"));
    }

    static public Task fromText(String description, String done, LocalDateTime from, LocalDateTime to) {
        Task task = new Event(description, from, to);
        task.isDone = done.equals("1");
        return task;
    }
}
