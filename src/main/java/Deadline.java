import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;
    public Deadline(String desc, LocalDate by) {
        super(desc);
        this.by = by;
    }

    public Deadline(boolean isDone, String desc, LocalDate by) {
        super(isDone, desc);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.getStatusIcon(),
                this.description,
                this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
