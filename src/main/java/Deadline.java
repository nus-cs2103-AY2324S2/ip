import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String byString = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma"));
        return "[D]" + super.toString() + "(by:" + byString + ")";
    }
}

