import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    static protected String ALIAS = "E";

    public Event(String taskName, boolean status, LocalDateTime from, LocalDateTime to) {
        super(taskName, status);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + ALIAS + "]" + super.toString() + " (from: " + from.format(dateTimeString) + " to: " + to.format(dateTimeString)  + ")";
    }

    @Override
    public String toStore() {
        return ALIAS + super.toStore() + "," + from.format(Duke.dateTimeFormatter)  + "," + to.format(Duke.dateTimeFormatter);
    }
}
