import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate byWhen;
    public Deadline(String description, LocalDate byWhen, boolean done) {
        super(description, done);
        this.byWhen = byWhen;
    }

    public LocalDate getByWhen() {
        return byWhen;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), byWhen.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
