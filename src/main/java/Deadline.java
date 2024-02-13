import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime by;

    public Deadline(String name, Boolean status, LocalDateTime by) {
        super(name,status);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + "(by: " + by.format(formatter) + ")";
    }
}
