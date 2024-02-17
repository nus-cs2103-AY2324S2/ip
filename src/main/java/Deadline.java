import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime by;

    public Deadline(String taskDescription, Boolean isDone, LocalDateTime by) {
        super(taskDescription);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + "(by: " + by.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        return "D| " + super.toFileString() + " | " + this.by;
    }


}
