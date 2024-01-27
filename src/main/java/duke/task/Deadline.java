package duke.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private String deadlineString;

    public Deadline(String description, boolean hasDone, LocalDateTime deadline) {
        this.deadline = deadline;
        this.deadlineString = deadline.format(DateTimeFormatter.ofPattern("MM dd yyyy HH:mm"));
        super.setDescription(description);
        super.setHasDone(hasDone);
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getDeadlineString() {
        return deadlineString;
    }

    public void setDeadlineString(String deadlineString) {
        this.deadlineString = deadlineString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineString + ")";
    }
}
