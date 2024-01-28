import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public static final String TYPE_SYMBOL = "D";
    private final LocalDateTime due;
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadline(String description, LocalDateTime due) {
        super(description);
        this.due = due;
    }

    public Deadline(String description, boolean isDone, LocalDateTime due) {
        super(description, isDone);
        this.due = due;
    }

    private String formatDate() {
        return this.due.format(DATE_TIME_FORMAT);
    }

    @Override
    public String toCsv() {
        return TYPE_SYMBOL + "," + (super.getDone() ? "1" : "0") + "," + super.getDescription() + ","
                + formatDate() + ",";
    }

    @Override
    public String toString() {
        return "[" + TYPE_SYMBOL + "]" + super.toString() + " (by: " + formatDate() + ")";
    }
}
