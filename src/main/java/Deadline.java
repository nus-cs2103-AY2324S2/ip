import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        super.isDone = false;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + " " + dueDate.getDayOfMonth() + " " + dueDate.getMonth()
                + " " + dueDate.getYear() + " " + dueDate.getHour() + ":" + dueDate.getMinute() + ")";
    }
}
