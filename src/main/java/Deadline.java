import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected String byInput;
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        this.byInput = by;
    }

    @Override
    public String toString() {
        String byString = this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, byString);
    }

    public String toString2() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.byInput);
    }
}
