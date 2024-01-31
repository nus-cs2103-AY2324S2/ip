package jivox.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime deadline;

    public Deadline(String content, LocalDateTime deadline) {
        super(content);
        this.deadline = deadline;
    }


    public String getType() {
        return "D";
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public String saveFormat() {
        return this.getType() + " | " + (this.getStatus() ? "1" : "0") + " | " + this.getDescription()
                + " | " + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                + ")";
    }
}
