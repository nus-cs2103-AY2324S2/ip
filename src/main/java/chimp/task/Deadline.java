package chimp.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String text, TaskStatus status, LocalDate by) {
        super(text, status);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] "
                + super.toString()
                + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " )";
    }
}
