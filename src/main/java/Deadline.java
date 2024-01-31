import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Deadline extends Task {
    LocalDateTime deadline;
    public Deadline(String name, LocalDateTime deadline, boolean isDone) {
        this.name = name;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format("[D]%s (by: %s)", super.toString(), deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        return str;
    }

    @Override
    public String convertToText() {
        String str = "";
        str = String.format("%s event %s /by %s", isDone, name, deadline);
        return str;
    }
}
