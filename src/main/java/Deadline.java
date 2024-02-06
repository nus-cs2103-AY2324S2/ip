import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;
    static protected String ALIAS = "D";

    public Deadline(String taskName, boolean status, LocalDateTime by) {
        super(taskName, status);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + ALIAS + "]" + super.toString() + " (by: " + by.format(dateTimeString)  + ")";
    }

    @Override
    public String toStore() {
        return ALIAS + super.toStore() + "," + by.format(Duke.dateTimeFormatter);
    }
}
