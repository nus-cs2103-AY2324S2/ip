import java.time.LocalDateTime;

public class Deadline extends Task {
    public static final String TYPE_SYMBOL = "D";
    private final LocalDateTime due;

    public Deadline(String description, LocalDateTime due) {
        super(description);
        this.due = due;
    }

    public Deadline(String description, boolean isDone, LocalDateTime due) {
        super(description, isDone);
        this.due = due;
    }

    @Override
    public String toCsv() {
        return TYPE_SYMBOL + "," + (super.getDone() ? "1" : "0") + "," + super.getDescription() + ","
                + Parser.dateTimeToString(this.due) + ",";
    }

    @Override
    public String toString() {
        return "[" + TYPE_SYMBOL + "]" + super.toString() + " (by: " + Parser.dateTimeToString(this.due) + ")";
    }
}
