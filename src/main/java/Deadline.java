import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
        this.type = "D";
    }

    public String getStatus() {
        String statusIcon = (isDone ? "X" : " ");
        return "[" + type + "][" + statusIcon + "] " + description + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:ma")) + ")";
    }

    public String toText() {
        return super.toText() + " / " + deadline.format(DateTimeFormatter.ofPattern("yyyy MM dd H m"));
    }

    static public Task fromText(String description, String done, LocalDateTime by) {
        Task task = new Deadline(description, by);
        task.isDone = done.equals("1");
        return task;
    }
}
