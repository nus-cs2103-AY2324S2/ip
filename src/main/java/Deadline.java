import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime by;
    public Deadline(String name, LocalDateTime by) {
        this(name, by, false);
    }

    public Deadline(String name, LocalDateTime by, boolean mark) {
        super(name, mark);
        this.by = by;
    }

    @Override
    public String toSaveString() {
        return String.format("D\t%s\t%s", super.toSaveString(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    @Override
    public String toString(DateTimeFormatter dtf) {
        return String.format("[D]%s (by: %s)", super.toString(), dtf.format(by));
    }
}
